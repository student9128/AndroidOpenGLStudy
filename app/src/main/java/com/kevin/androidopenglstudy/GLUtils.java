package com.kevin.androidopenglstudy;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kevin on 2020/5/21<br/>
 * Blog:http://student9128.top/
 * 公众号：竺小竹
 * Describe:<br/>
 * 工具类
 */
public class GLUtils {
    public static int loadShaderAssets(Context context, int type, String name) {
        String result = null;
        try {
            InputStream inputStream = context.getAssets().open(name);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = inputStream.read()) != -1) {
                baos.write(ch);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            inputStream.close();
            result = new String(bytes);
            result = result.replaceAll("\\r\\n", "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadShader(type, result);
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);//创建着色器
        if (shader == 0) {//加载失败
            return 0;
        }
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return checkCompile(type, shader);
    }

    private static int checkCompile(int type, int shader) {
        int[] compiled = new int[1];//存放编译成功shader数量的数组
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e("GLES20_COMPILE_ERROR", "Could not compile shader " + type + ":" + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
        }
        return shader;
    }
}
