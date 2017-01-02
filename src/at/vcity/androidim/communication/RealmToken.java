package at.vcity.androidim.communication;

import java.util.ArrayList;

import at.vcity.androidim.domain.DomainRealmToken;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by root on 28/02/16.
 */
public class RealmToken {

    private Realm realm = Realm.getDefaultInstance();



    protected void createdData(String accesToken, String setRefresh_token, String bearer){
        realm.beginTransaction();
        DomainRealmToken realmTokenNya = realm.createObject(DomainRealmToken.class);
        realmTokenNya.setToken(accesToken);
        realmTokenNya.setRefresh_token(setRefresh_token);
        realmTokenNya.setBearer(bearer);
        realm.commitTransaction();
        //realm.cancelTransaction();
    }
    protected void updateData(String accesToken, String setRefresh_token, String bearer) {
        realm.beginTransaction();
        DomainRealmToken realmTokenNya = realm.where(DomainRealmToken.class).equalTo("token", accesToken).findFirst();
        realmTokenNya.setToken(accesToken);
        realmTokenNya.setRefresh_token(setRefresh_token);
        realmTokenNya.setBearer(bearer);
        realm.commitTransaction();
    }
    protected void deleteData(String accesToken) {
        RealmResults<DomainRealmToken> dataDesults = realm.where(DomainRealmToken.class).equalTo("token", accesToken).findAll();
        realm.beginTransaction();
        //dataDesults.remove(0);
        //dataDesults.removeLast();
        dataDesults.clear();
        realm.commitTransaction();
    }
    protected void deleteDataAllToken() {
        RealmResults<DomainRealmToken> dataDesults = realm.where(DomainRealmToken.class).findAll();
        realm.beginTransaction();
        dataDesults.clear();
        realm.commitTransaction();
    }
    protected DomainRealmToken readData(){
        RealmResults<DomainRealmToken> r = realm.where(DomainRealmToken.class) .findAll();
        DomainRealmToken domain = new DomainRealmToken();
        System.out.println("ukuran : " + r.size());
        for (int a = 0; a < r.size(); a++ ){
            domain.setToken(r.get(a).getToken());
            domain.setBearer(r.get(a).getBearer());
            domain.setRefresh_token(r.get(a).getRefresh_token());
        }
        return domain;
    }

}
