package cm.android.app.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import cm.android.app.global.Tag;
import cm.android.wdcommondapi.R;

public class ConfirmDialog extends DialogFragment {

    public static ConfirmDialog newInstance(String title, String content, boolean singleConfirm) {
        ConfirmDialog myDialogFragment = new ConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString(Tag.TITLE, title);
        bundle.putString(Tag.CONTENT, content);
        bundle.putBoolean(Tag.SINGLE_CONFIRM, singleConfirm);
        myDialogFragment.setArguments(bundle);
        return myDialogFragment;
    }

    private IConfirm iConfirm = defaultConfirm;
    private static final IConfirm defaultConfirm = new IConfirm() {
        @Override
        public void onPositive() {
        }

        @Override
        public void onNegative() {
        }
    };

    public void setOnClickListener(IConfirm iConfirm) {
        if (iConfirm != null) {
            this.iConfirm = iConfirm;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        boolean single = false;
        String title = "";
        String content = "";
        if (bundle != null) {
            single = bundle.getBoolean(Tag.SINGLE_CONFIRM, false);
            title = bundle.getString(Tag.TITLE);
            content = bundle.getString(Tag.CONTENT);
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        iConfirm.onPositive();
                    }
                });
        if (!single) {
            dialog.setNegativeButton(R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            iConfirm.onNegative();
                        }
                    });
        }
        return dialog.show();
    }

    public static interface IConfirm {
        void onPositive();

        void onNegative();
    }
}