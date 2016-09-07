package com.nitesh.spark.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.nitesh.spark.R;
import com.nitesh.spark.exception.EasyRuntimeException;

import java.util.List;


/**
 * Created by Nitesh Singh(killer) on 8/31/2016.
 */
public class EasyRecyclerView extends RecyclerView implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;
    private OnLoadMoreListener mLoadMoreListener;
    private int itemCount;
    private int lastVisibleItem;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean isLoading;
    private int assumedNoOfVisbileItem =5;
    private int previousLastVisibleItem;
    private final Context mContext;
    private GestureDetector mGestureDetector;
    private View mProgressView;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

    public EasyRecyclerView(Context context) {
        this(context,null);
    }

    public EasyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EasyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    /**
     * This will add onItemClick listener
     * @param listener
     */
    public void addOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
        mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        addOnItemTouchListener(this);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        mLinearLayoutManager = (LinearLayoutManager) layout;
    }

    /**
     * This will add load more listener
     * This method should be called after calling setLayoutManager(LayoutManager manager) method
     * @param listener
     */
    public void addLoadMoreListener(OnLoadMoreListener listener) {

        if(mLinearLayoutManager!=null && getAdapter()!=null){
            ((EasyAdapter)getAdapter()).setLoadMoreEnabled(true);
            mLoadMoreListener = listener;
            addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    itemCount = mLinearLayoutManager.getItemCount();
                    previousLastVisibleItem = lastVisibleItem;
                    lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && itemCount <= (lastVisibleItem + assumedNoOfVisbileItem) && previousLastVisibleItem<lastVisibleItem) {

                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }else{
            throw new EasyRuntimeException("addLoadMoreListener should be called after setAdapter method");
        }
    }

    public View getmProgressView() {
        return mProgressView;
    }

    /**
     * You have to call this method when your API execution will be complted
     * For Example : for Async task- in PostExecite
     * For Example : for HTTP library in onResponse and similar method
     */
    public void setDataLoadingFromServerCompleted(){
        isLoading = false;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            if((view.getChildAdapterPosition(childView)-((EasyAdapter)getAdapter()).getOffSet())>=0){
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView)-((EasyAdapter)getAdapter()).getOffSet());
                return true;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
    public static abstract class EasyAdapter<NITSVH extends ViewHolder> extends Adapter<NITSVH>{
        private static final int ITEM = 0;
        private static final int HEADER = -1;
        private static final int FOOTER = 1;
        private View mHeader, mFooter;
        private List list;
        private boolean isFooterManuallySet;
        private boolean isLoadMoreEnabled;

        public EasyAdapter(Context context , List list) {
            this.list = list;
            mFooter = LayoutInflater.from(context).inflate(R.layout.footer_progess,null);
        }
        @Override
        public NITSVH onCreateViewHolder(ViewGroup parent, int viewType){
            NITSVH viewHolder;
            if (viewType == HEADER) {
                viewHolder = onCreateHeaderViewHolder(parent,mHeader);
            } else if (viewType == FOOTER && (isFooterManuallySet || isLoadMoreEnabled)) {
                viewHolder = onCreateFooterViewHolder(parent,mFooter);
            } else {
                viewHolder = onCreateItemViewHolder(parent, viewType);
            }
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(NITSVH holder, int position){
            if (isHeaderPosition(position)) {
                onBindHeaderViewHolder(holder, position);
            } else if (isFooterPosition(position)) {
                onBindFooterViewHolder(holder, position);
            } else {
                onBindItemViewHolder(holder, position-getOffSet());
            }
        }

        private int getOffSet() {
            return ((mHeader!=null)?1:0);
        }

        protected abstract void onBindItemViewHolder(NITSVH holder, int position);
        protected abstract void onBindFooterViewHolder(NITSVH holder, int position);
        protected abstract void onBindHeaderViewHolder(NITSVH holder, int position);

        @Override
        public int getItemViewType(int position) {
            int viewType = ITEM;
            if (isHeaderPosition(position)) {
                viewType = HEADER;
            } else if (isFooterPosition(position)) {
                viewType = FOOTER;
            }
            return viewType;
        }

        @Override
        public final int getItemCount(){
            return (list.size())+((mHeader!=null)?1:0)+((mFooter!=null)?1:0);
        }

        protected abstract NITSVH onCreateHeaderViewHolder(ViewGroup parent, View headerView);
        protected abstract NITSVH onCreateItemViewHolder(ViewGroup parent, int position);
        protected abstract NITSVH onCreateFooterViewHolder(ViewGroup parent, View footerView);

        public boolean isHeaderPosition(int position) {
            return (mHeader!=null) && position == 0;
        }
        public boolean isFooterPosition(int position) {
            return (mFooter!=null) && position == (getItemCount() - 1);
        }

        public void setHeaderView(View view){
            mHeader = view;
        }

        public void setFooterView(View view){
            setFooterManuallySet(true);
            mFooter = view;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public boolean isLoadMoreEnabled() {
            return isLoadMoreEnabled;
        }

        public void setLoadMoreEnabled(boolean loadMoreEnabled) {
            isLoadMoreEnabled = loadMoreEnabled;
        }

        public boolean isFooterManuallySet() {
            return isFooterManuallySet;
        }

        public void setFooterManuallySet(boolean footerManuallySet) {
            isFooterManuallySet = footerManuallySet;
        }
    }
}


