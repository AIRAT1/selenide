package page;

public interface Checked<T> {
    T checkOpen();

    default T checkOpen(String titleInfo) {
        return checkOpen();
    }
}

