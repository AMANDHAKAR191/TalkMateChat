package com.talkmate.aman.messages;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.talkmate.aman.R;
import com.talkmate.aman.data.MyPreference;
import com.talkmate.aman.home.PasswordGeneratorActivity;

public class AddContactEmailDialogFragment extends DialogFragment {

    TextInputLayout tilAddUser;
    String senderUid;
    MyPreference myPreference;
    Context context;
    private String chatRoomID;


    public AddContactEmailDialogFragment(Context context ,String senderUid) {
        this.senderUid = senderUid;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_dialog_add_contact_email, container, false);
        tilAddUser = view.findViewById(R.id.til_add_user);

        myPreference = MyPreference.getInstance(context);
        tilAddUser.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = tilAddUser.getEditText().getText().toString();
                createChatRoomId(userName);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("messageUserList");
                Query checkUser = reference.orderByChild("publicUid").equalTo(userName);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                UserListModelClass userListModel = ds.getValue(UserListModelClass.class);
                                String tempUserId = userListModel.getPublicUid();
                                String tempUserName = userListModel.getPublicUname();
                                String commonEncryptionKey = PasswordGeneratorActivity.generateRandomPassword(22, true, true, true, false) + "==";
                                String commonEncryptionIv = PasswordGeneratorActivity.generateRandomPassword(16, true, true, true, false);

                                UserPersonalChatList personalChatList = new UserPersonalChatList(tempUserId, tempUserName, chatRoomID, commonEncryptionKey, commonEncryptionIv, true, false, ".");
                                reference.child(senderUid).child("userPersonalChatList").child(tempUserId).setValue(personalChatList)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                myPreference.setChatRoomId(chatRoomID);
                                                dismiss();
                                            }
                                        });
                            }
                        } else {
                            tilAddUser.setError("No User Found!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }
    public void createChatRoomId(String userName){
        chatRoomID = myPreference.getPublicUid() + userName;
    }
}