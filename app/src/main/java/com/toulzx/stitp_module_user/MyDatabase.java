package com.toulzx.stitp_module_user;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.toulzx.stitp_module_user.dao.DataDao;
import com.toulzx.stitp_module_user.dao.UserDao;
import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.entity.User;

@androidx.room.Database(
        entities = {User.class, Data.class},
        version = 1
)
public abstract class MyDatabase extends RoomDatabase {
    public static final String DATABASE_FILE_NAME = "database";

    private static MyDatabase INSTANCE;

    // singleton: 懒汉单例模式，因为 database 实例化消耗较大资源
    // synchronized 保证多进程运行时排队进行，而不冲突
    public static synchronized MyDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_FILE_NAME)
                    .fallbackToDestructiveMigration()       // 破坏式迁移，会清空原有数据
                    .allowMainThreadQueries()       // 允许主线程运行
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDao getUserDao();
    public abstract DataDao getDataDao();

}
