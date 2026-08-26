package com.jvm.mastery.module0.lab02;

/**
 * =====================================================================
 * CỐT LÕI DECORATOR PATTERN: BaseNotifierDecorator (Abstract Decorator)
 * =====================================================================
 *
 * Điểm mấu chốt của Decorator:
 * 1. Nó IMPLEMENT Notifier (để có cùng kiểu dữ liệu với đối tượng được bọc).
 * 2. Nó CHỨA 1 biến wrapped (tham chiếu đến Notifier bên trong).
 *
 * Nhờ vậy, Decorator có thể bọc 1 Notifier gốc, HOẶC bọc 1 Decorator khác!
 */
public abstract class BaseNotifierDecorator implements Notifier {

    protected final Notifier wrapped;

    protected BaseNotifierDecorator(Notifier wrapped) {
        if (wrapped == null) {
            throw new IllegalArgumentException("Wrapped notifier cannot be null");
        }
        this.wrapped = wrapped;
    }

    @Override
    public void send(String recipient, String message) {
        // Mặc định chuyển tiếp lời gọi cho đối tượng bên trong (delegate)
        wrapped.send(recipient, message);
    }

    @Override
    public String getChannelName() {
        return wrapped.getChannelName();
    }
}
