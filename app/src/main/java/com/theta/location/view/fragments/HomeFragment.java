package com.theta.location.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.theta.location.R;
import com.theta.location.adapters.UserAdaptes;
import com.theta.location.apiCall.GetDataService;
import com.theta.location.apiCall.RetrofitClientInstance;
import com.theta.location.databinding.FragmentHomeBinding;
import com.theta.location.models.UserModel;
import com.theta.location.utils.ConnectionCheck;
import com.theta.location.utils.EndlessScrollListener;
import com.theta.location.utils.LogFile;
import com.theta.location.utils.Utils;
import com.theta.location.viewmodel.UserViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Home Fragment
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : Recharge Fragment
 * <p>
 * -> Make a International Recharge of mobile number
 * -> Manage Country wise
 */
public class HomeFragment extends Fragment {

    private View view;
    private ConnectionCheck connectionCheck;

    //    private RecyclerView rvList;
    private UserAdaptes adapter;
    private List<UserViewModel> userModelList = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    /*pagination vars start*/
    private boolean loading = true;
    int page = 1;
    int total_pages = 6;
    boolean isLastPage = false;
    /*pagination vars end*/

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        view = binding.getRoot();

        connectionCheck = new ConnectionCheck();

        adapter = new UserAdaptes(getActivity(), userModelList);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvList.setLayoutManager(layoutManager);
        binding.rvList.setItemAnimator(new DefaultItemAnimator());
        binding.rvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        page = 1;
        if (connectionCheck.isNetworkConnected(getActivity())) {
            setData(true);
        }

        binding.rvList.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int p, int totalItemsCount) {
                if (loading) {
                    loading = false;
                    page++;
                    if (!isLastPage) {
                        setData(false);
                    }
                }
            }
        });

        return view;
    }

    /**
     * Set Data
     */
    private void setData(boolean clearFlag) {

        Utils.openDialog(getActivity());

        if (clearFlag) {
            userModelList.clear();
        }

        //Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<UserModel> call = service.getAllUsers(page);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                Utils.closeDialog();
                LogFile.i("URL :- " + call.request().url());
                UserModel userModel = response.body();
                if (userModelList != null && userModel.getData() != null && userModel.getData().size() > 0) {
                    Gson gson = new Gson();
                    String json = gson.toJson(userModel);
                    setUserData(json);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Utils.closeDialog();
                Utils.showToast(getActivity(), "Something went wrong...Please try later!", false);
            }
        });
    }

    private void setUserData(String data) {

        try {

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                UserViewModel dataList = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), UserViewModel.class);
                userModelList.add(dataList);
            }

            if (jsonArray.length() < total_pages || jsonArray.length() == 0) {
                isLastPage = true;
            } else {
                isLastPage = false;
            }
            loading = true;
            if (userModelList.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
