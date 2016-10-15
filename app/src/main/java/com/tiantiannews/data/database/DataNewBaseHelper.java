package com.tiantiannews.data.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.tiantiannews.base.BaseModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class DataNewBaseHelper<T> {

    private final BriteDatabase mDb;

    private DbInterface db;

    @Inject
    public DataNewBaseHelper(DbOpenHelper dbOpenHelper) {
        mDb = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());

    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<BaseModel<T>> setFriends(final Collection<BaseModel<T>> newFriends) {
        return Observable.create(new Observable.OnSubscribe<BaseModel<T>>() {
            @Override
            public void call(Subscriber<? super BaseModel<T>> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(DbInterface., null);
                    for (BaseModel<T> friends : newFriends) {
                        long result = mDb.insert(Db.FriendsProfileTable.TABLE_NAME,
                                Db.FriendsProfileTable.toContentValues(friends.profile),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(friends);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<BaseModel<T>>> getFriends() {
        return mDb.createQuery(Db.FriendsProfileTable.TABLE_NAME,
                "SELECT * FROM " + Db.FriendsProfileTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, BaseModel<T>>() {
                    @Override
                    public BaseModel<T> call(Cursor cursor) {
//                        return new BaseModel<>(Db.FriendsProfileTable.parseCursor(cursor));
                        return  null;
                    }
                });
    }

}
