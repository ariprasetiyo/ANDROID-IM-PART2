package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import at.vcity.androidim.domain.DomainRealmToken;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DomainRealmTokenRealmProxy extends DomainRealmToken
    implements RealmObjectProxy {

    static final class DomainRealmTokenColumnInfo extends ColumnInfo {

        public final long tokenIndex;
        public final long refresh_tokenIndex;
        public final long bearerIndex;

        DomainRealmTokenColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.tokenIndex = getValidColumnIndex(path, table, "DomainRealmToken", "token");
            indicesMap.put("token", this.tokenIndex);

            this.refresh_tokenIndex = getValidColumnIndex(path, table, "DomainRealmToken", "refresh_token");
            indicesMap.put("refresh_token", this.refresh_tokenIndex);

            this.bearerIndex = getValidColumnIndex(path, table, "DomainRealmToken", "bearer");
            indicesMap.put("bearer", this.bearerIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DomainRealmTokenColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("token");
        fieldNames.add("refresh_token");
        fieldNames.add("bearer");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DomainRealmTokenRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DomainRealmTokenColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public String getToken() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.tokenIndex);
    }

    @Override
    public void setToken(String value) {
        realm.checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field token to null.");
        }
        row.setString(columnInfo.tokenIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getRefresh_token() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.refresh_tokenIndex);
    }

    @Override
    public void setRefresh_token(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.refresh_tokenIndex);
            return;
        }
        row.setString(columnInfo.refresh_tokenIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getBearer() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.bearerIndex);
    }

    @Override
    public void setBearer(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.bearerIndex);
            return;
        }
        row.setString(columnInfo.bearerIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_DomainRealmToken")) {
            Table table = transaction.getTable("class_DomainRealmToken");
            table.addColumn(RealmFieldType.STRING, "token", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "refresh_token", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "bearer", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("token"));
            table.setPrimaryKey("token");
            return table;
        }
        return transaction.getTable("class_DomainRealmToken");
    }

    public static DomainRealmTokenColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_DomainRealmToken")) {
            Table table = transaction.getTable("class_DomainRealmToken");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DomainRealmTokenColumnInfo columnInfo = new DomainRealmTokenColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("token")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'token' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("token") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'token' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.tokenIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'token' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'token' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("token")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'token' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("token"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'token' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("refresh_token")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'refresh_token' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("refresh_token") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'refresh_token' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.refresh_tokenIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'refresh_token' is required. Either set @Required to field 'refresh_token' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("bearer")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'bearer' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("bearer") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'bearer' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.bearerIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'bearer' is required. Either set @Required to field 'bearer' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The DomainRealmToken class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_DomainRealmToken";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static DomainRealmToken createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        DomainRealmToken obj = null;
        if (update) {
            Table table = realm.getTable(DomainRealmToken.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("token")) {
                long rowIndex = table.findFirstString(pkColumnIndex, json.getString("token"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new DomainRealmTokenRealmProxy(realm.schema.getColumnInfo(DomainRealmToken.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("token")) {
                if (json.isNull("token")) {
                    obj = realm.createObject(DomainRealmToken.class, null);
                } else {
                    obj = realm.createObject(DomainRealmToken.class, json.getString("token"));
                }
            } else {
                obj = realm.createObject(DomainRealmToken.class);
            }
        }
        if (json.has("token")) {
            if (json.isNull("token")) {
                obj.setToken(null);
            } else {
                obj.setToken((String) json.getString("token"));
            }
        }
        if (json.has("refresh_token")) {
            if (json.isNull("refresh_token")) {
                obj.setRefresh_token(null);
            } else {
                obj.setRefresh_token((String) json.getString("refresh_token"));
            }
        }
        if (json.has("bearer")) {
            if (json.isNull("bearer")) {
                obj.setBearer(null);
            } else {
                obj.setBearer((String) json.getString("bearer"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static DomainRealmToken createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        DomainRealmToken obj = realm.createObject(DomainRealmToken.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("token")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setToken(null);
                } else {
                    obj.setToken((String) reader.nextString());
                }
            } else if (name.equals("refresh_token")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setRefresh_token(null);
                } else {
                    obj.setRefresh_token((String) reader.nextString());
                }
            } else if (name.equals("bearer")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setBearer(null);
                } else {
                    obj.setBearer((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static DomainRealmToken copyOrUpdate(Realm realm, DomainRealmToken object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        DomainRealmToken realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(DomainRealmToken.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (object.getToken() == null) {
                throw new IllegalArgumentException("Primary key value must not be null.");
            }
            long rowIndex = table.findFirstString(pkColumnIndex, object.getToken());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new DomainRealmTokenRealmProxy(realm.schema.getColumnInfo(DomainRealmToken.class));
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static DomainRealmToken copy(Realm realm, DomainRealmToken newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        DomainRealmToken realmObject = realm.createObject(DomainRealmToken.class, newObject.getToken());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setToken(newObject.getToken());
        realmObject.setRefresh_token(newObject.getRefresh_token());
        realmObject.setBearer(newObject.getBearer());
        return realmObject;
    }

    public static DomainRealmToken createDetachedCopy(DomainRealmToken realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<DomainRealmToken> cachedObject = (CacheData) cache.get(realmObject);
        DomainRealmToken standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new DomainRealmToken();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setToken(realmObject.getToken());
        standaloneObject.setRefresh_token(realmObject.getRefresh_token());
        standaloneObject.setBearer(realmObject.getBearer());
        return standaloneObject;
    }

    static DomainRealmToken update(Realm realm, DomainRealmToken realmObject, DomainRealmToken newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setRefresh_token(newObject.getRefresh_token());
        realmObject.setBearer(newObject.getBearer());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DomainRealmToken = [");
        stringBuilder.append("{token:");
        stringBuilder.append(getToken());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{refresh_token:");
        stringBuilder.append(getRefresh_token() != null ? getRefresh_token() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bearer:");
        stringBuilder.append(getBearer() != null ? getBearer() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainRealmTokenRealmProxy aDomainRealmToken = (DomainRealmTokenRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aDomainRealmToken.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aDomainRealmToken.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aDomainRealmToken.row.getIndex()) return false;

        return true;
    }

}
