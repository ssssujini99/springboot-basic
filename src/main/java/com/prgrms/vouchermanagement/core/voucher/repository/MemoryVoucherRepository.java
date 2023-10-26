package com.prgrms.vouchermanagement.core.voucher.repository;

import com.prgrms.vouchermanagement.core.voucher.domain.Voucher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Profile("dev")
@Repository
public class MemoryVoucherRepository implements VoucherRepository {

    private final List<Voucher> voucherArrayList = new CopyOnWriteArrayList<>();

    @Override
    public Voucher save(Voucher voucher) {
        voucherArrayList.add(voucher);
        return voucher;
    }

    @Override
    public List<Voucher> findAll() {
        return voucherArrayList;
    }
}
