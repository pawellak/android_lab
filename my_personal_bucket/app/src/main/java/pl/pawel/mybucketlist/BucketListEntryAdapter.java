package pl.pawel.mybucketlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BucketListEntryAdapter extends RecyclerView.Adapter<BucketListEntryAdapter.BucketListEntryViewHolder> {

    public BucketListEntryAdapter(BucketListEntry[] bucketListEntries) {
        this.bucketListEntries = bucketListEntries;
    }

    final BucketListEntry[] bucketListEntries;

    @Override
    public int getItemCount() {
        return bucketListEntries.length;
    }

    @NonNull
    @Override
    public BucketListEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bucket_list_entry, parent, false);
        return new BucketListEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketListEntryViewHolder holder, int position) {
        holder.bind(bucketListEntries[position], position);
    }

    static class BucketListEntryViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textText;
        private final TextView descriptionText;
        private final RatingBar ratingBar;


        public BucketListEntryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view_item);
            textText = itemView.findViewById(R.id.text_view_item_heading);
            descriptionText = itemView.findViewById(R.id.text_view_item_description);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }

        public void bind(BucketListEntry bucketListEntry, int position) {
            String myHeader = (position+1) +". " + bucketListEntry.heading;
            imageView.setImageResource(bucketListEntry.image);
            textText.setText(myHeader);
            descriptionText.setText(bucketListEntry.description);
            ratingBar.setRating(bucketListEntry.rating);
        }
    }
}
