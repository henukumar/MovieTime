package com.kalher.movietime.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.kalher.movietime.common.interfaces.IRecyclerItem;
import com.kalher.movietime.common.interfaces.IShow;

public class ShowItem extends AbstractItem implements IShow, IRecyclerItem {

    public static DiffUtil.ItemCallback<ShowItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<ShowItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull ShowItem oldItem, @NonNull ShowItem newItem) {
            return oldItem.getId() == newItem.getId();
//            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShowItem oldItem, @NonNull ShowItem newItem) {
            return oldItem.equals(newItem);
//            return false;
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        // compare content of movie object, since we aren't modifying any content
        // so comparing only id will be sufficient
        return ((ShowItem) obj).getId() == this.id;
//        return false;
    }


}
