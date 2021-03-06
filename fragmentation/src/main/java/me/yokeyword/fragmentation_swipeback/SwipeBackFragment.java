package me.yokeyword.fragmentation_swipeback;

import android.os.Bundle;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import android.view.View;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment;
import me.yokeyword.fragmentation_swipeback.core.SwipeBackFragmentDelegate;

/**
 * You can also refer to {@link SwipeBackFragment} to implement YourSwipeBackFragment
 * (extends Fragment and impl {@link me.yokeyword.fragmentation.ISupportFragment})
 * <p>
 * Created by YoKey on 16/4/19.
 */
public class SwipeBackFragment extends SupportFragment implements ISwipeBackFragment {
    SwipeBackFragmentDelegate mDelegate = new SwipeBackFragmentDelegate(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkNull();
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkNull();
        mDelegate.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View attachToSwipeBack(View view) {
        return mDelegate.attachToSwipeBack(view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        checkNull();
        mDelegate.onHiddenChanged(hidden);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        checkNull();
        return mDelegate.getSwipeBackLayout();
    }

    /**
     * 是否可滑动
     *
     * @param enable
     */
    public void setSwipeBackEnable(boolean enable) {
        checkNull();
        mDelegate.setSwipeBackEnable(enable);
    }

    @Override
    public void setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel) {
        checkNull();
        mDelegate.setEdgeLevel(edgeLevel);
    }

    @Override
    public void setEdgeLevel(int widthPixel) {
        checkNull();
        mDelegate.setEdgeLevel(widthPixel);
    }

    /**
     * Set the offset of the parallax slip.
     */
    public void setParallaxOffset(@FloatRange(from = 0.0f, to = 1.0f) float offset) {
        checkNull();
        mDelegate.setParallaxOffset(offset);
    }

    @Override
    public void onDestroyView() {
        checkNull();
        mDelegate.onDestroyView();
        super.onDestroyView();
    }

    public void checkNull(){
        if (mDelegate == null){
            mDelegate = new SwipeBackFragmentDelegate(this);
        }
    }
}