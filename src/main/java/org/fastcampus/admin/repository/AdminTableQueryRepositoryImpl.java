package org.fastcampus.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fastcampus.admin.ui.dto.GetTableListResponse;
import org.fastcampus.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.admin.ui.query.AdminTableQueryRepository;
import org.fastcampus.auth.repository.entity.QUserAuthEntity;
import org.fastcampus.auth.repository.entity.UserAuthEntity;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;


    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableDate(GetUserTableRequestDto dto) {
        int total = jpaQueryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(
                        likeName(dto.getName())
                )
                .fetch()
                .size();

        List<Long> ids = jpaQueryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(
                        likeName(dto.getName())
                )
                .orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();


        List<GetUserTableResponseDto> result = jpaQueryFactory
                .select(
                        Projections.fields(
                                GetUserTableResponseDto.class,
                                userEntity.id.as("id"),
                                userAuthEntity.email.as("email"),
                                userEntity.name.as("name"),
                                userAuthEntity.role.as("role"),
                                userEntity.regDt.as("createdAt"),
                                userEntity.updDt.as("updatedAt"),
                                userAuthEntity.lastLoginDt.as("lastLoginAt")
                        )
                )
                .from(userEntity)
                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
                .where(
                        userEntity.id.in(ids)
                )
                .orderBy(userEntity.id.desc())
                .fetch();

        return new GetTableListResponse<>(total, result);
    }

    private BooleanExpression likeName(String name){
        if(name == null || name.isBlank()){
            return null;
        }
        return userEntity.name.like(name + "%");
    }
}
