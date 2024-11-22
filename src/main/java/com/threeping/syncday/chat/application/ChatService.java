package com.threeping.syncday.chat.application;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.dto.ChatRoomDTO;
import com.threeping.syncday.chat.entity.ChatMessage;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.repository.ChatMessageRepository;
import com.threeping.syncday.chat.repository.ChatRoomRepository;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository,
                       ChatRoomRepository chatRoomRepository,
                       UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }


    //  채팅방 목록 조회
    public List<ChatRoom> findUserChat(String userId) {
        return chatRoomRepository.findByMemberId(userId);
    }

    //  특정 채팅방 조회
    public ChatRoom findChatRoomByRoomId(String roomId) {

        return chatRoomRepository.findChatRoomByRoomId(roomId).orElseThrow(
                () -> new IllegalArgumentException("해당 {roomId}채팅방이 없습니다." + roomId));

    }

    //  채팅방 생성(멤버 필터)
    public ChatRoom createChatRoom(ChatRoomDTO chatRoomDTO) {
        // 멤버 기반 이름 설정
//            List<String> memberIds = chatRoomDTO.getMemberIds();
//            String chatRoomName = memberIds.size() == 2
//                    ? "1:1 채팅 - " + memberIds.get(0) + " & " + memberIds.get(1)
//                    : String.join(", ", memberIds);
//
//            // 1:1 채팅방 중복 방지
//            if (memberIds.size() == 2) {
//                ChatRoom existingRoom = chatRoomRepository.find1to1ChatRoom(memberIds.get(0), memberIds.get(1));
//                if (existingRoom != null) {
//                    return existingRoom; // 이미 존재하면 해당 방 반환
//                }
//            }

        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(chatRoomDTO.getRoomId())
                .chatRoomName(String.join(",", chatRoomDTO.getMemberIds()))
                .creatorId(chatRoomDTO.getCreatorId())
                .createdAt(LocalDateTime.now())
                .memberCount(chatRoomDTO.getMemberIds().size())
                .memberIds(chatRoomDTO.getMemberIds())
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    //  특정 채팅방 입장
//        public ChatRoom joinChatRoom (String roomId, String userId) {
//            ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId ( roomId );
//
//            if (chatRoom == null) {
//                throw new IllegalArgumentException ("채팅방이 존재하지 않습니다.");
//            }
//            chatRoom.setMemberCount(chatRoom.getMemberCount () + 1);
//            return chatRoomRepository.save ( chatRoom );
//        }

    //  특정 채팅방 퇴장
    public void leaveChatRoom(String roomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        chatRoom.getMemberIds().remove(userId);
        chatRoom.setMemberCount(chatRoom.getMemberIds().size());

        if (chatRoom.getMemberCount() == 0) {   // 채팅방에 멤버가 1명이면 자동 삭제됨
            chatRoomRepository.delete(chatRoom);
        } else {
            chatRoomRepository.save(chatRoom);
        }
    }


        // 채팅방 이름 수정(단체톡방 일 시)
        public ChatRoom updateRoomName(String roomId, String newName) {
            ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

            chatRoom.setChatRoomName(newName);
            return chatRoomRepository.save(chatRoom);
        }
        //  채팅방 채팅 작성
        public ChatMessage createMessage(String roomId, ChatMessageDTO chatMessageDTO) {
            ChatMessage chatMessage = ChatMessage.builder ()
                    .messageId ( chatMessageDTO.getMessageId () )
                    .message ( chatMessageDTO.getMessage () )
                    .roomId ( chatMessageDTO.getRoomId () )
                    .senderId ( chatMessageDTO.getSenderId () )
                    .sentTime (LocalDateTime.now())
                    .receiverId ( chatMessageDTO.getReceiverId () )
                    .memberIds ( chatMessageDTO.getMemberIds () )
                    .memberCount ( chatMessageDTO.getMemberCount () )
                    .chatType ( chatMessageDTO.getChatType () )
                    .build ();
            return chatMessageRepository.save ( chatMessage );
        }
//  채팅방&채팅 내용 검색

//  첨부 파일 업로드
}

