package uni.oulu.hci.restaurantmenu;

/**
 * http://stackoverflow.com/questions/3948934/synchronise-scrollview-scroll-positions-android
 */
public interface ScrollViewListener {
	void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
}
