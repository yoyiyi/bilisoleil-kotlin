package com.yoyiyi.soleil.bean.region

import android.os.Parcel
import android.os.Parcelable

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 11:47
 * 描述:
 */
data class RegionTagType(var tid: Int,
                         var reid: Int,
                         var name: String,
                         var logo: String,
                         var goto: String,
                         var param: String,
                         var children: List<Children>) : Parcelable {


    constructor(`in`: Parcel) : this(`in`.readInt(),
            `in`.readInt(),
            `in`.readString(),
            `in`.readString(),
            `in`.readString(),
            `in`.readString(),
            children = arrayListOf()) {
        `in`.readList(children, Children::class.java.classLoader)

    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.tid)
        dest.writeInt(this.reid)
        dest.writeString(this.name)
        dest.writeString(this.logo)
        dest.writeString(this.goto)
        dest.writeString(this.param)
        dest.writeList(this.children)

    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RegionTagType> = object : Parcelable.Creator<RegionTagType> {
            override fun createFromParcel(source: Parcel): RegionTagType {
                return RegionTagType(source)
            }

            override fun newArray(size: Int): Array<RegionTagType?> {
                return arrayOfNulls(size)
            }
        }
    }


    data class Children(var tid: Int,
                        var reid: Int,
                        var name: String,
                        var logo: String,
                        var goto: String,
                        var param: String) : Parcelable {
        constructor(`in`: Parcel) : this(`in`.readInt(),
                `in`.readInt(),
                `in`.readString(),
                `in`.readString(),
                `in`.readString(),
                `in`.readString()
        )

        companion object {
            @JvmField val CREATOR: Parcelable.Creator<Children> = object : Parcelable.Creator<Children> {
                override fun createFromParcel(source: Parcel): Children {
                    return Children(source)
                }

                override fun newArray(size: Int): Array<Children?> {
                    return arrayOfNulls(size)
                }
            }

        }


        override fun describeContents(): Int = 0


        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(this.tid)
            dest.writeInt(this.reid)
            dest.writeString(this.name)
            dest.writeString(this.logo)
            dest.writeString(this.goto)
            dest.writeString(this.param)


        }
    }
}


