package com.tiantiannews.data.modle;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public abstract class PageListDataModel<T> {

    protected ListPageInfo<T> mListPageInfo;//由子类实现
    protected List<BaseModel<T>> listData; //解析的数据
    protected String mUrlPart; //交由子类实现

//    /**
//     * 数据加载完成的回调
//     */
//    private PagedListDataHandler mPagedListDataHandler;
//
//    public interface PagedListDataHandler {
//        void onPageDataLoaded(ListPageInfo<?> listPageInfo);
//    }
//
//    public void setPageListDataHandler(PagedListDataHandler handler) {
//        mPagedListDataHandler = handler;
//    }

    /**
     * 必须实现的方法，联网请求数据
     */
    protected abstract void doQueryData();

    public void queryFirstPage() {
        checkPageInfo();
        mListPageInfo.goToHead();
        doQueryDataInner();
    }

    public void queryNextPage() {
        checkPageInfo();
        if (mListPageInfo.prepareForNextPage()) {
            doQueryDataInner();
        }
    }

    private void checkPageInfo() {
        if (mListPageInfo == null) {
            throw new IllegalArgumentException(" mListPageInfo has not been initialized.");
        }
    }

    private void doQueryDataInner() {
        if (!mListPageInfo.tryEnterLock()) {
            return;
        }
        if (isReadCacheData()) {

        } else {
            doQueryData();
        }
    }

    protected abstract String getCacheKeyPrefix();

    protected String getCacheKey() {
        return new StringBuilder(getCacheKeyPrefix()).append("_")
                .append(mListPageInfo.getPage()).toString();
    }

    protected void readCacheData() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });
    }

    protected void saveCacheData() {
    }

    /**
     * 判断是否需要读取缓存的数据
     *
     * @return
     */
    protected boolean isReadCacheData() {
        if (!TDevice.hasInternet()) {
            return true; //读取缓存
        }
        return false;  //联网请求数据
    }

//    protected Response.Listener<String> listener = new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            onRequestResponse(response);
//        }
//    };
//
//    protected Response.ErrorListener errorListener = new StrErrListener() {
//        @Override
//        public void onErrorResponse(VolleyError arg0) {
//            onRequestError();
//        }
//    };
//
//    protected void onRequestResponse(String response) {
//
//        listData = parseListData(response);
//
//        boolean mHasMore = true;
//        if (listData != null && listData.size() < mListPageInfo.getNumPerPage()) {
//            mHasMore = false;
//        }
//        ListDataEvent<T> event = new ListDataEvent<>();
//        event.hasMore = mHasMore;
//        event.newsList = listData;
//        event.url = mUrlPart;
//        event.listPageInfo = mListPageInfo;
//        setRequestResult(listData, mHasMore);
//        EventCenter.getInstance().post(event);
//    }
//
//    protected void onRequestError() {
//        setRequestFail();
//    }

    /**
     * 由子类实现
     *
     * @param is
     * @return
     */
    protected ArrayList<List<BaseModel<T>>> parseListData(String is) {
        return null;
    }

    protected void setRequestResult(List<BaseModel<T>> list) {
        mListPageInfo.updateListInfo(list); //只是改变数据，需要调用adapter change更新
    }

    protected void setRequestResult(List<BaseModel<T>> list, int total) {
        mListPageInfo.updateListInfo(list, total);
    }

    protected void setRequestResult(List<BaseModel<T>> list, boolean hasMore) {
        mListPageInfo.updateListInfo(list, hasMore);
    }

    protected void setRequestFail() {
        mListPageInfo.rollbackOnFail();
    }

    public ListPageInfo<T> getListPageInfo() {
        return mListPageInfo;
    }
}
