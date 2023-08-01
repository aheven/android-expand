package skin.support.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import skin.support.annotation.NonNull;

public interface SkinLayoutInflater {
    View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs);
}
