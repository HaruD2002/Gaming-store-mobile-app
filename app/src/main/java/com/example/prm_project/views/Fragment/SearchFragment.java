package com.example.prm_project.views.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.prm_project.R;
import com.example.prm_project.viewmodel.ShopViewModel;
import com.example.prm_project.viewmodel.UserViewModel;
import com.example.prm_project.views.ChangePasswordActivity;
import com.example.prm_project.views.ListShopActivity;
import com.example.prm_project.views.UserProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private ShopViewModel shopViewModel;
    private SearchView searchView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVM();
        bindingView(view);
        bindingAction();
    }

    public void bindingView(View view){
        searchView = view.findViewById(R.id.search_bar_search_fragment);
    }

    public void bindingAction(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý khi người dùng nhấn nút tìm kiếm
                Intent intent = new Intent(getContext(), ListShopActivity.class);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("SHOP_SEARCH", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SHOP_TEXT", searchView.getQuery().toString());
                editor.apply();
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý khi người dùng nhập hoặc xóa ký tự trong SearchView
                return true;
            }
        });    }

    private void getVM(){
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}