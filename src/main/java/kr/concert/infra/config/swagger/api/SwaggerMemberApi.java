package kr.concert.infra.config.swagger.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.concert.interfaces.member.MemberRequest;
import kr.concert.interfaces.member.MemberResponse;
import kr.concert.interfaces.presentation.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Member", description = "회원 관련 API")
public interface SwaggerMemberApi {

    @Operation(summary = "포인트 잔액 조회", description = "해당 회원의 포인트 잔액을 조회합니다.")
    ApiResponse<MemberResponse.GetPoint> getPoint(@PathVariable Long memberId);

    @Operation(summary = "포인트 충전", description = "해당 회원의 포인트를 충전합니다.")
    ApiResponse<MemberResponse.ChargePoint> chargePoint(@PathVariable Long memberId, @RequestBody MemberRequest.ChargePoint chargePointRequest);
}
