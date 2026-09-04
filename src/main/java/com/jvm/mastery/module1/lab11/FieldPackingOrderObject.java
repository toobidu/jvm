package com.jvm.mastery.module1.lab11;

/**
 * Class khai báo các field xen kẽ kích thước:
 * - byte (1 byte)
 * - long (8 bytes)
 * - int (4 bytes)
 * - boolean (1 byte)
 *
 * Để xem JVM có lưu đúng thứ tự khai báo không, hay tự động đảo thứ tự (Field Reordering)
 * để nhồi nhét tối ưu bộ nhớ!
 */
public class FieldPackingOrderObject {
    private byte a = 1;
    private long b = 100L;
    private int c = 42;
    private boolean d = true;
}
