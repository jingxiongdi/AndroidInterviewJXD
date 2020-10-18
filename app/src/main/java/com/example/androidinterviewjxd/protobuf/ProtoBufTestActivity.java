package com.example.androidinterviewjxd.protobuf;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;
import com.example.androidinterviewjxd.StudentProto;
import com.example.androidinterviewjxd.utils.FileUtils;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.File;
import java.io.IOException;

public class ProtoBufTestActivity extends AppCompatActivity {
    public static final String TAG = "ProtoBufTestActivity_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proto_buf_test);

        StudentProto.Student student1 = StudentProto.Student.newBuilder()
                .setName("jxd")
                .setAge(28)
                .setId(111)
                .build();
        byte[] bytes = student1.toByteArray();
        File stuFile = new File(getCacheDir().getParent()+File.separator+"stuproto.proto");
        try{
            if(!stuFile.exists()){
                stuFile.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        FileUtils.writeByteArrToFile(stuFile.getAbsolutePath(),bytes);
        try {
            StudentProto.Student student2 =  StudentProto.Student.parseFrom(bytes);
            Log.d(TAG,student2.getId() + "," + student2.getName() + "," + student2.getAge());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
