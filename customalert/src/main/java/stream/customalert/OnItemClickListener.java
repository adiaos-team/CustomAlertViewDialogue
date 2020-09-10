package stream.customalert;

import stream.customalert.CustomAlertDialogue;
import stream.customalert.DialogItemInfo;

public interface OnItemClickListener {
    /**
     *
     * @param dialog  可以用来dismiss
     * @param dialogItemInfo 针对显示内容和value不同的情况使用。比如name-value格式。
     * @param name 针对简单的string列表使用
     */
        void onItemClick(CustomAlertDialogue dialog, DialogItemInfo dialogItemInfo,String name);
    }