package ir.ariaman.ariakala.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import ir.ariaman.ariakala.R;
import ir.ariaman.ariakala.model.Repository;
import ir.ariaman.ariakala.model.jsonschema.category.Category;

public class HomePageFragment extends Fragment {
    private final static String PARAMS_SCROLL_VIEW_X = "SCROLL_VIEW_X";
    private final static String PARAMS_SCROLL_VIEW_Y = "SCROLL_VIEW_Y";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Integer> usedCategoriesId;
    private ScrollView scrollView;
    private Bundle viewState;
    private int scrollViewX, scrollViewY;

    public HomePageFragment() {
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        init(view);
        return view;
    }

    public void init(View view) {
        scrollView = view.findViewById(R.id.fragment_home_page_scroll_view);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                ViewTreeObserver observer = scrollView.getViewTreeObserver();
                observer.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        scrollViewX = scrollView.getScrollX();
                        scrollViewY = scrollView.getScrollY();
                    }
                });
            }
        });
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        usedCategoriesId = new ArrayList<>();
        fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        initSlider();
        initCircularCategories();
        initAmazingProducts();
        initRandomCategoriesBlock();
        initRandomCategoryHorizontalProducts();
        initRandomCategoryProductTable();
        initRandomCategoryProductsInRow();
        initRandomCategoryProductTable(
                R.id.fragment_home_page_random_category_product_table_two_frame_layout);
        initRandomCategoryProductsInRow(
                R.id.fragment_home_page_random_category_product_in_row_two_frame_layout);
    }

    private void initCircularCategories() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_circular_categories_frame_layout,
                CircularCategoriesFragment.newInstance())
                .commit();
    }

    private void initSlider() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_slider_frame_layout,
                HomePageSliderFragment.newInstance())
                .commit();
    }

    private void initAmazingProducts() {
        usedCategoriesId.add(119);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_amazing_products_frame_layout,
                ProductsHorizontalRecyclerFragment.newInstance())
                .commit();
    }

    private void initRandomCategoriesBlock() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_random_categories_block_frame_layout,
                RandomCategoryBlockFragment.newInstance())
                .commit();
    }

    private void initRandomCategoryHorizontalProducts() {
        List<Category> allCategories = new ArrayList<>();
        for (Category category : Repository.getInstance().getCategories()) {
            if (!usedCategoriesId.contains(category.getId())) {
                allCategories.add(category);
            }
        }
        if (allCategories.size() == 0) {
            allCategories = Repository.getInstance().getCategories();
        }
        Random random = new Random();
        int index = random.nextInt(allCategories.size());
        Category category = allCategories.get(index);
        usedCategoriesId.add(category.getId());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_random_category_horizontal_products_frame_layout,
                ProductsHorizontalRecyclerFragment.newInstance(category.getId()))
                .commit();
    }

    private void initRandomCategoryProductTable() {
        List<Category> allCategories = new ArrayList<>();
        for (Category category : Repository.getInstance().getCategories()) {
            if (!usedCategoriesId.contains(category.getId())) {
                allCategories.add(category);
            }
        }
        Random random = new Random();
        int index = random.nextInt(allCategories.size());
        Category category = allCategories.get(index);
        usedCategoriesId.add(category.getId());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_random_category_product_table_frame_layout,
                RandomCategoryProductsTableFragment.newInstance(category.getId()))
                .commit();
    }

    private void initRandomCategoryProductTable(int frameId) {
        List<Category> allCategories = new ArrayList<>();
        for (Category category : Repository.getInstance().getCategories()) {
            if (!usedCategoriesId.contains(category.getId())) {
                allCategories.add(category);
            }
        }
        if (allCategories.size() == 0) {
            allCategories = Repository.getInstance().getCategories();
        }
        Random random = new Random();
        int index = random.nextInt(allCategories.size());
        Category category = allCategories.get(index);
        usedCategoriesId.add(category.getId());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                frameId,
                RandomCategoryProductsTableFragment.newInstance(category.getId()))
                .commit();
    }

    private void initRandomCategoryProductsInRow() {
        List<Category> allCategories = new ArrayList<>();
        for (Category category : Repository.getInstance().getCategories()) {
            if (!usedCategoriesId.contains(category.getId())) {
                allCategories.add(category);
            }
        }
        if (allCategories.size() == 0) {
            allCategories = Repository.getInstance().getCategories();
        }
        Random random = new Random();
        int index = random.nextInt(allCategories.size());
        Category category = allCategories.get(index);
        usedCategoriesId.add(category.getId());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.fragment_home_page_random_category_product_in_row_frame_layout,
                RandomCategoryProductsInRowFragment.newInstance(category.getId()))
                .commit();
    }

    private void initRandomCategoryProductsInRow(int frameId) {
        List<Category> allCategories = new ArrayList<>();
        for (Category category : Repository.getInstance().getCategories()) {
            if (!usedCategoriesId.contains(category.getId())) {
                allCategories.add(category);
            }
        }
        if (allCategories.size() == 0) {
            allCategories = Repository.getInstance().getCategories();
        }
        Random random = new Random();
        int index = random.nextInt(allCategories.size());
        Category category = allCategories.get(index);
        usedCategoriesId.add(category.getId());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                frameId,
                RandomCategoryProductsInRowFragment.newInstance(category.getId()))
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        viewState = new Bundle();
        viewState.putInt(PARAMS_SCROLL_VIEW_X, scrollViewX);
        viewState.putInt(PARAMS_SCROLL_VIEW_Y, scrollViewY);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewState != null) {
            scrollViewX = viewState.getInt(PARAMS_SCROLL_VIEW_X);
            scrollViewY = viewState.getInt(PARAMS_SCROLL_VIEW_Y);
            scrollView.scrollTo(scrollViewX, scrollViewY);
        }
    }
}
