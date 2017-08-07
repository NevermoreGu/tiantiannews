//package com.tiantiannews.data.database;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.squareup.sqlbrite.BriteDatabase;
//import com.squareup.sqlbrite.SqlBrite;
//import com.tiantiannews.base.ApiResponse;
//
//import java.util.Collection;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import rx.Observable;
//import rx.Subscriber;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//
//@Singleton
//public class DataNewBaseHelper<T> {
//
//    private final BriteDatabase mDb;
//
//    private DbInterface db;
//
//    @Inject
//    public DataNewBaseHelper(DbOpenHelper dbOpenHelper) {
//        mDb = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
//
//    }
//
//    public BriteDatabase getBriteDb() {
//        return mDb;
//    }
//
//    public Observable<ApiResponse<T>> setFriends(final Collection<ApiResponse<T>> newFriends) {
//        return Observable.create(new Observable.OnSubscribe<ApiResponse<T>>() {
//            @Override
//            public void call(Subscriber<? super ApiResponse<T>> subscriber) {
//                if (subscriber.isUnsubscribed()) return;
//                BriteDatabase.Transaction transaction = mDb.newTransaction();
//                try {
//                    mDb.delete(DbInterface., null);
//                    for (ApiResponse<T> friends : newFriends) {
//                        long result = mDb.insert(Db.FriendsProfileTable.TABLE_NAME,
//                                Db.FriendsProfileTable.toContentValues(friends.profile),
//                                SQLiteDatabase.CONFLICT_REPLACE);
//                        if (result >= 0) subscriber.onNext(friends);
//                    }
//                    transaction.markSuccessful();
//                    subscriber.onCompleted();
//                } finally {
//                    transaction.end();
//                }
//            }
//        });
//    }
//
//    public Observable<List<ApiResponse<T>>> getFriends() {
//        return mDb.createQuery(Db.FriendsProfileTable.TABLE_NAME,
//                "SELECT * FROM " + Db.FriendsProfileTable.TABLE_NAME)
//                .mapToList(new Func1<Cursor, ApiResponse<T>>() {
//                    @Override
//                    public ApiResponse<T> call(Cursor cursor) {
////                        return new ApiResponse<>(Db.FriendsProfileTable.parseCursor(cursor));
//                        return  null;
//                    }
//                });
//    }
//
//}
