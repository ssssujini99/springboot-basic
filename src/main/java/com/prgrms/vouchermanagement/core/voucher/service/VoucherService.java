package com.prgrms.vouchermanagement.core.voucher.service;

import com.prgrms.vouchermanagement.core.voucher.domain.Voucher;
import com.prgrms.vouchermanagement.core.voucher.dto.VoucherDto;
import com.prgrms.vouchermanagement.core.voucher.repository.VoucherRepository;
import com.prgrms.vouchermanagement.infra.exception.InvalidFormatException;
import com.prgrms.vouchermanagement.infra.utils.MenuPatternUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.prgrms.vouchermanagement.core.voucher.utils.mapper.Mapper.toVoucher;
import static com.prgrms.vouchermanagement.core.voucher.utils.mapper.Mapper.toVoucherDto;

@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public VoucherDto create(VoucherDto voucherDto) {
        Voucher voucher = voucherRepository.save(toVoucher(voucherDto));
        return new VoucherDto(voucher.getId(), voucher.getName(), voucher.getAmount(), voucher.getVoucherType());
    }

    public List<VoucherDto> findAll() {
        List<Voucher> voucherList = voucherRepository.findAll();
        return voucherList.stream()
                .map(it -> new VoucherDto(it.getId(), it.getName(), it.getAmount(), it.getVoucherType()))
                .collect(Collectors.toList());
    }

    public VoucherDto getVoucher(String voucherId) {
        Optional<Voucher> optionalVoucher = voucherRepository.findById(voucherId);
        if (optionalVoucher.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 바우처 id 입니다.");
        }
        return toVoucherDto(optionalVoucher.get());
    }


    public String deleteById(String voucherId) {
        voucherRepository.deleteById(voucherId);
        return voucherId;
    }

    public List<VoucherDto> findByVoucherType(String voucherType) {
        if (!MenuPatternUtils.VOUCHER_TYPE.matcher(voucherType).matches()) {
            throw new InvalidFormatException("voucherType은 [fixed, rate] 중 하나이어야 합니다.");
        }
        List<Voucher> voucherList = voucherRepository.findAll();
        return voucherList.stream()
                .filter(it -> it.getVoucherType().getValue().equals(voucherType))
                .map(it -> new VoucherDto(it.getId(), it.getName(), it.getAmount(), it.getVoucherType()))
                .collect(Collectors.toList());
    }
}
