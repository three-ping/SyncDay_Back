package com.threeping.syncday.chat.repository;

import com.threeping.syncday.chat.dto.ChatRoomDTO;
import com.threeping.syncday.chat.entity.ChatRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findChatRoomByRoomId (String roomId );

//    @Query("SELECT c FROM ChatRoom c WHERE userId MEMBER OF c.memberIds")
//    List<ChatRoom> findByMemberId(@Param("userId") Long userId);   // 사용자가 포함된 채팅방만 조회
}
