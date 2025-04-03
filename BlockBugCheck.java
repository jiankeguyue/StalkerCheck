package com.xxx.injectprotect.StalkerCheck;

import android.content.Context;
import android.util.Log;

import com.huxin3.injectprotect.CheckLib;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BlockBugCheck {
    private static final String TAG = "InjectDetection";

    private static final byte[] BLOCK_BUG_CHECK_ONE = new byte[]{
            (byte) 0xC0, (byte) 0x03, (byte) 0x5F, (byte) 0xD6  // ret
    };

    private static final byte[] BLOCK_BUG_CHECK_TWO = new byte[]{
            (byte) 0x00, (byte) 0x8C, (byte) 0x01, (byte) 0x91, // add x0, x0, #99
            (byte) 0xC0, (byte) 0x03, (byte) 0x5F, (byte) 0xD6  // ret
    };
    private Context mContext;

    public BlockBugCheck(Context context) {
        this.mContext = context;
    }

    public static void checkBlockBug(int nums) {

        String[] results = CheckLib.doStalkerBugCheck(BLOCK_BUG_CHECK_ONE, BLOCK_BUG_CHECK_TWO, nums);
        for (String result : results) {
            Log.i(TAG, result);
        }
    }
}
