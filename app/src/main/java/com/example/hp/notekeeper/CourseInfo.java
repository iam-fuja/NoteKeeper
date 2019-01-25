package com.example.hp.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jim.
 */

public final class CourseInfo implements Parcelable {
    private final String mCourseId;
    private final String mTitle;
    private final List<com.example.hp.notekeeper.ModuleInfo> mModules;

    public CourseInfo(String courseId, String title, List<com.example.hp.notekeeper.ModuleInfo> modules) {
        mCourseId = courseId;
        mTitle = title;
        mModules = modules;
    }

    private CourseInfo(Parcel source) {
        mCourseId = source.readString();
        mTitle = source.readString();
        mModules = new ArrayList<>();
        source.readTypedList(mModules, ModuleInfo.CREATOR);
    }

    /* this area was added. this controls the */

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    public String getCourseId() {
        return mCourseId;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<com.example.hp.notekeeper.ModuleInfo> getModules() {
        return mModules;
    }

    public boolean[] getModulesCompletionStatus() {
        boolean[] status = new boolean[mModules.size()];

        for (int i = 0; i < mModules.size(); i++)
            status[i] = mModules.get(i).isComplete();

        return status;
    }

    public void setModulesCompletionStatus(boolean[] status) {
        for (int i = 0; i < mModules.size(); i++)
            mModules.get(i).setComplete(status[i]);
    }

    public com.example.hp.notekeeper.ModuleInfo getModule(String moduleId) {
        for (com.example.hp.notekeeper.ModuleInfo moduleInfo : mModules) {
            if (moduleId.equals(moduleInfo.getModuleId()))
                return moduleInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseInfo that = (CourseInfo) o;

        return mCourseId.equals(that.mCourseId);

    }

    @Override
    public int hashCode() {
        return mCourseId.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //This code had an issue
        //dest.writeParcelable( mModules, 0);
        dest.writeString(mCourseId);
        dest.writeString(mTitle);
        dest.writeTypedList(mModules);

    }
}
/*
    public final static Parcelable.Creator<CourseInfo> CREATOR = new Parcelable.Creator<CourseInfo>(){

        @Override
        public CourseInfo createFromParcel(Parcel source) {
            return new CourseInfo(source) ;
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };


}*/
