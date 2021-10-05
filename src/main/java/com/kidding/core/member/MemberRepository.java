package com.kidding.core.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
