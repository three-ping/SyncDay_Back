package com.threeping.syncday.vcs.query.repository;

import com.threeping.syncday.vcs.query.aggregate.vo.ReqUserInstallationCheck;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VcsUserMapper {
    Boolean checkUserInstallation(ReqUserInstallationCheck reqUserInstallationCheck);
}
