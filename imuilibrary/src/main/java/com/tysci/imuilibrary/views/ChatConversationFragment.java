package com.tysci.imuilibrary.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tysci.applibrary.base.BaseFragment;
import com.tysci.imuilibrary.R;
import com.tysci.imuilibrary.views.chatviews.ChatInputMenu;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2015/12/24.
 */
public class ChatConversationFragment extends Fragment {
    private View contentView;
    private RecyclerView rvMessage;
    private PtrClassicFrameLayout ptrRefresh;
    private ChatInputMenu chatInputMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fragment_chat_conversation,container,false);
        initViews(contentView);
        return contentView;
    }

    private void initViews(View view){
        rvMessage=(RecyclerView)view.findViewById(R.id.rv_message);
        ptrRefresh=(PtrClassicFrameLayout)view.findViewById(R.id.ptr_message);
        chatInputMenu=(ChatInputMenu)view.findViewById(R.id.chat_input_menu);
    }
}
