package com.poa.tempscanner.mips;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
//import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.Objects;

//@Metadata(bv = {1, 0, 3}, d1 = {"\000(\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\002\b\005\n\002\020\016\n\000\n\002\020\006\n\002\b\034\n\002\020\013\n\002\b\004\b\b\030\0002\0020\001By\022\b\b\002\020\002\032\0020\003\022\b\b\002\020\004\032\0020\003\022\n\b\002\020\005\032\004\030\0010\003\022\b\b\002\020\006\032\0020\003\022\b\b\002\020\007\032\0020\003\022\n\b\002\020\b\032\004\030\0010\t\022\n\b\002\020\n\032\004\030\0010\013\022\b\b\002\020\f\032\0020\003\022\b\b\002\020\r\032\0020\003\022\b\b\002\020\016\032\0020\003\022\b\b\002\020\017\032\0020\003\006\002\020\020J\t\020\032\032\0020\003H\003J\t\020\033\032\0020\003H\003J\t\020\034\032\0020\003H\003J\t\020\035\032\0020\003H\003J\020\020\036\032\004\030\0010\003H\003\006\002\020\022J\t\020\037\032\0020\003H\003J\t\020 \032\0020\003H\003J\013\020!\032\004\030\0010\tH\003J\020\020\"\032\004\030\0010\013H\003\006\002\020\027J\t\020#\032\0020\003H\003J\t\020$\032\0020\003H\003J\001\020%\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\n\b\002\020\005\032\004\030\0010\0032\b\b\002\020\006\032\0020\0032\b\b\002\020\007\032\0020\0032\n\b\002\020\b\032\004\030\0010\t2\n\b\002\020\n\032\004\030\0010\0132\b\b\002\020\f\032\0020\0032\b\b\002\020\r\032\0020\0032\b\b\002\020\016\032\0020\0032\b\b\002\020\017\032\0020\003H\001\006\002\020&J\023\020'\032\0020(2\b\020)\032\004\030\0010\001H\003J\t\020*\032\0020\003H\001J\t\020+\032\0020\tH\001R\026\020\006\032\0020\0038\006X\004\006\b\n\000\032\004\b\006\020\021R\026\020\004\032\0020\0038\006X\004\006\b\n\000\032\004\b\004\020\021R\026\020\r\032\0020\0038\006X\004\006\b\n\000\032\004\b\r\020\021R\026\020\f\032\0020\0038\006X\004\006\b\n\000\032\004\b\f\020\021R\026\020\002\032\0020\0038\006X\004\006\b\n\000\032\004\b\002\020\021R\026\020\007\032\0020\0038\006X\004\006\b\n\000\032\004\b\007\020\021R\026\020\017\032\0020\0038\006X\004\006\b\n\000\032\004\b\017\020\021R\032\020\005\032\004\030\0010\0038\006X\004\006\n\n\002\020\023\032\004\b\005\020\022R\030\020\b\032\004\030\0010\t8\006X\004\006\b\n\000\032\004\b\024\020\025R\032\020\n\032\004\030\0010\0138\006X\004\006\n\n\002\020\030\032\004\b\026\020\027R\026\020\016\032\0020\0038\006X\004\006\b\n\000\032\004\b\031\020\021\006,"}, d2 = {"Lcom/lamasatech/visipoint/webServer/MipsApi/TemperatureConfig;", "", "isLowTempAdopt", "", "isBodyTempStart", "isWearingMask", "isBodyTempAlarm", "isStandardTempAdopt", "standardBodyTemp", "", "tempCompensation", "", "isLowFeverAdopt", "isHighFeverAdopt", "tempCompensationParam", "isStrangerRecord", "(IILjava/lang/Integer;IILjava/lang/String;Ljava/lang/Double;IIII)V", "()I", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStandardBodyTemp", "()Ljava/lang/String;", "getTempCompensation", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getTempCompensationParam", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(IILjava/lang/Integer;IILjava/lang/String;Ljava/lang/Double;IIII)Lcom/lamasatech/visipoint/webServer/MipsApi/TemperatureConfig;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final class TemperatureConfig {
    @SerializedName("isBodyTempAlarm")
    private final int isBodyTempAlarm;

    @SerializedName("isBodyTempStart")
    private final int isBodyTempStart;

    @SerializedName("isHighFeverAdopt")
    private final int isHighFeverAdopt;

    @SerializedName("isLowFeverAdopt")
    private final int isLowFeverAdopt;

    @SerializedName("isLowTempAdopt")
    private final int isLowTempAdopt;

    @SerializedName("isStandardTempAdopt")
    private final int isStandardTempAdopt;

    @SerializedName("isStrangerRecord")
    private final int isStrangerRecord;

    @SerializedName("isWearingMask")
    private final Integer isWearingMask;

    @SerializedName("standardBodyTemp")
    private final String standardBodyTemp;

    @SerializedName("tempCompensation")
    private final Double tempCompensation;

    @SerializedName("tempCompensationParam")
    private final int tempCompensationParam;

    public TemperatureConfig() {
        this(0, 0, null, 0, 0, null, null, 0, 0, 0, 0);
    }

    public TemperatureConfig(int paramInt1, int paramInt2, Integer paramInteger, int paramInt3, int paramInt4, String paramString, Double paramDouble, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
        this.isLowTempAdopt = paramInt1;
        this.isBodyTempStart = paramInt2;
        this.isWearingMask = paramInteger;
        this.isBodyTempAlarm = paramInt3;
        this.isStandardTempAdopt = paramInt4;
        this.standardBodyTemp = paramString;
        this.tempCompensation = paramDouble;
        this.isLowFeverAdopt = paramInt5;
        this.isHighFeverAdopt = paramInt6;
        this.tempCompensationParam = paramInt7;
        this.isStrangerRecord = paramInt8;
    }

    public final int component1() {
        return this.isLowTempAdopt;
    }

    public final int component10() {
        return this.tempCompensationParam;
    }

    public final int component11() {
        return this.isStrangerRecord;
    }

    public final int component2() {
        return this.isBodyTempStart;
    }

    public final Integer component3() {
        return this.isWearingMask;
    }

    public final int component4() {
        return this.isBodyTempAlarm;
    }

    public final int component5() {
        return this.isStandardTempAdopt;
    }

    public final String component6() {
        return this.standardBodyTemp;
    }

    public final Double component7() {
        return this.tempCompensation;
    }

    public final int component8() {
        return this.isLowFeverAdopt;
    }

    public final int component9() {
        return this.isHighFeverAdopt;
    }

    public final TemperatureConfig copy(int paramInt1, int paramInt2, Integer paramInteger, int paramInt3, int paramInt4, String paramString, Double paramDouble, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
        return new TemperatureConfig(paramInt1, paramInt2, paramInteger, paramInt3, paramInt4, paramString, paramDouble, paramInt5, paramInt6, paramInt7, paramInt8);
    }

    public boolean equals(Object paramObject) {
        if (this != paramObject) {
            if (paramObject instanceof TemperatureConfig) {
                paramObject = paramObject;
                if (this.isLowTempAdopt == ((TemperatureConfig)paramObject).isLowTempAdopt && this.isBodyTempStart == ((TemperatureConfig)paramObject).isBodyTempStart && Intrinsics.areEqual(this.isWearingMask, ((TemperatureConfig)paramObject).isWearingMask) && this.isBodyTempAlarm == ((TemperatureConfig)paramObject).isBodyTempAlarm && this.isStandardTempAdopt == ((TemperatureConfig)paramObject).isStandardTempAdopt && Intrinsics.areEqual(this.standardBodyTemp, ((TemperatureConfig)paramObject).standardBodyTemp) && Intrinsics.areEqual(this.tempCompensation, ((TemperatureConfig)paramObject).tempCompensation) && this.isLowFeverAdopt == ((TemperatureConfig)paramObject).isLowFeverAdopt && this.isHighFeverAdopt == ((TemperatureConfig)paramObject).isHighFeverAdopt && this.tempCompensationParam == ((TemperatureConfig)paramObject).tempCompensationParam && this.isStrangerRecord == ((TemperatureConfig)paramObject).isStrangerRecord)
                    return true;
            }
            return false;
        }
        return true;
    }

    public final String getStandardBodyTemp() {
        return this.standardBodyTemp;
    }

    public final Double getTempCompensation() {
        return this.tempCompensation;
    }

    public final int getTempCompensationParam() {
        return this.tempCompensationParam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBodyTempAlarm, isBodyTempStart, isHighFeverAdopt, isLowFeverAdopt, isLowTempAdopt, isStandardTempAdopt, isStrangerRecord, isWearingMask, standardBodyTemp, tempCompensation, tempCompensationParam);
    }

    public final int isBodyTempAlarm() {
        return this.isBodyTempAlarm;
    }

    public final int isBodyTempStart() {
        return this.isBodyTempStart;
    }

    public final int isHighFeverAdopt() {
        return this.isHighFeverAdopt;
    }

    public final int isLowFeverAdopt() {
        return this.isLowFeverAdopt;
    }

    public final int isLowTempAdopt() {
        return this.isLowTempAdopt;
    }

    public final int isStandardTempAdopt() {
        return this.isStandardTempAdopt;
    }

    public final int isStrangerRecord() {
        return this.isStrangerRecord;
    }

    public final Integer isWearingMask() {
        return this.isWearingMask;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TemperatureConfig(isLowTempAdopt=");
        stringBuilder.append(this.isLowTempAdopt);
        stringBuilder.append(", isBodyTempStart=");
        stringBuilder.append(this.isBodyTempStart);
        stringBuilder.append(", isWearingMask=");
        stringBuilder.append(this.isWearingMask);
        stringBuilder.append(", isBodyTempAlarm=");
        stringBuilder.append(this.isBodyTempAlarm);
        stringBuilder.append(", isStandardTempAdopt=");
        stringBuilder.append(this.isStandardTempAdopt);
        stringBuilder.append(", standardBodyTemp=");
        stringBuilder.append(this.standardBodyTemp);
        stringBuilder.append(", tempCompensation=");
        stringBuilder.append(this.tempCompensation);
        stringBuilder.append(", isLowFeverAdopt=");
        stringBuilder.append(this.isLowFeverAdopt);
        stringBuilder.append(", isHighFeverAdopt=");
        stringBuilder.append(this.isHighFeverAdopt);
        stringBuilder.append(", tempCompensationParam=");
        stringBuilder.append(this.tempCompensationParam);
        stringBuilder.append(", isStrangerRecord=");
        stringBuilder.append(this.isStrangerRecord);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
