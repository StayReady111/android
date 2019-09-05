package mega.privacy.android.app.modalbottomsheet.chatmodalbottomsheet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import mega.privacy.android.app.R;
import mega.privacy.android.app.lollipop.megachat.ChatActivityLollipop;
import mega.privacy.android.app.modalbottomsheet.UtilsModalBottomSheet;
import mega.privacy.android.app.utils.Util;

public class SendAttachmentChatBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    Context context;
    private BottomSheetBehavior mBehavior;
    private LinearLayout items_layout;
    private LinearLayout mainLinearLayout;
    private TextView titleSlidingPanel;
    private LinearLayout optionFromCloudLayout;
    private LinearLayout optionFromFileSystemLayout;
    private LinearLayout optionContactLayout;
    private LinearLayout optionLocationLayout;
    private int heightDisplay;

    private static void log(String log) {
        Util.log("SendAttachmentChatBottomSheetDialogFragment", log);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(final Dialog dialog, int style) {

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        heightDisplay = outMetrics.heightPixels;

        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.send_attatchment_chat_bottom_sheet, null);
        mainLinearLayout = contentView.findViewById(R.id.send_attachment_chat_bottom_sheet);
        items_layout = contentView.findViewById(R.id.send_attachment_chat_items_layout);

        titleSlidingPanel = contentView.findViewById(R.id.send_attachment_chat_title_text);
        optionFromCloudLayout = contentView.findViewById(R.id.send_attachment_chat_from_cloud_layout);
        optionFromFileSystemLayout = contentView.findViewById(R.id.send_attachment_chat_from_filesystem_layout);
        optionContactLayout = contentView.findViewById(R.id.send_attachment_chat_contact_layout);
        optionLocationLayout = contentView.findViewById(R.id.send_attachment_chat_location_layout);
        optionFromCloudLayout.setOnClickListener(this);
        optionFromFileSystemLayout.setOnClickListener(this);
        optionContactLayout.setOnClickListener(this);
        optionLocationLayout.setOnClickListener(this);

        titleSlidingPanel.setText(getString(R.string.context_send));
        dialog.setContentView(contentView);

        mBehavior = BottomSheetBehavior.from((View) mainLinearLayout.getParent());
        mBehavior.setPeekHeight(UtilsModalBottomSheet.getPeekHeight(items_layout, heightDisplay, context, 48));
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onClick(View v) {
        log("onClick");

        switch (v.getId()) {

            case R.id.send_attachment_chat_from_cloud_layout: {
                log("cloud option click");
                ((ChatActivityLollipop) context).sendFromCloud();
                break;
            }
            case R.id.send_attachment_chat_from_filesystem_layout: {
                log("filesystem option click");
                ((ChatActivityLollipop) context).sendFromFileSystem();
                break;
            }
            case R.id.send_attachment_chat_contact_layout: {
                log("contact option click");
                ((ChatActivityLollipop) context).sendContact();
                break;
            }
            case R.id.send_attachment_chat_location_layout: {
                log("location option click");
                ((ChatActivityLollipop) context).sendLocation();
                break;
            }
        }
        mBehavior = BottomSheetBehavior.from((View) mainLinearLayout.getParent());
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}