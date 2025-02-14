package com.prgrms.vouchermanagement.core.voucher.utils.mapper;

import com.prgrms.vouchermanagement.core.voucher.domain.Voucher;
import com.prgrms.vouchermanagement.core.voucher.dto.VoucherDto;

public class Mapper {

    public static Voucher toVoucher(VoucherDto voucherDto) {
        return new Voucher(voucherDto.getName(), voucherDto.getAmount(), voucherDto.getVoucherType().toString());
    }

    public static VoucherDto toVoucherDto(Voucher voucher) {
        return new VoucherDto(voucher.getId(), voucher.getName(), voucher.getAmount(), voucher.getVoucherType());
    }

}
