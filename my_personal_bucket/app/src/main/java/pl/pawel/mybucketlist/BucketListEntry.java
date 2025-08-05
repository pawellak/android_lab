package pl.pawel.mybucketlist;

import androidx.annotation.DrawableRes;

public class BucketListEntry {
    public BucketListEntry(String heading, String description, @DrawableRes int image, float ratingValue) {
        this.heading = heading;
        this.description = description;
        this.image = image;
        this.rating = ratingValue;
    }

    final String heading;
    final String description;
    final int image;
    final float rating;
}
