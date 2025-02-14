package com.prgrms.vouchermanagement.infra.output;

import com.prgrms.vouchermanagement.core.voucher.controller.response.VouchersResponse;
import com.prgrms.vouchermanagement.infra.utils.OutputMessage;
import org.springframework.stereotype.Component;

@Component
public class OutputImpl implements OutputProvider {

    @Override
    public void printMessage(String message) {
        System.out.print(message);
    }

    @Override
    public void printVoucherTypeMessage(String voucherType) {
        switch(voucherType) {
            case "fixed":
                System.out.print(OutputMessage.FIXAMOUNT_VOUCHER_AMOUNT);
                break;
            case "rate":
                System.out.print(OutputMessage.PERCENTDISCOUNT_VOUCHER_AMOUNT);
                break;
        }
    }

    @Override
    public void printMessage(VouchersResponse vouchersResponse) {
        vouchersResponse.getVoucherResponses()
                .stream()
                .forEach(it -> System.out.println(it));
    }
}
