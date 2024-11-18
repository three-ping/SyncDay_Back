package com.threeping.syncday.chat.service;

import com.threeping.syncday.chat.dto.ChatMessageDTO;
import com.threeping.syncday.chat.dto.ChatRoomDTO;
import com.threeping.syncday.chat.entity.ChatMessage;
import com.threeping.syncday.chat.entity.ChatRoom;
import com.threeping.syncday.chat.repository.ChatMessageRepository;
import com.threeping.syncday.chat.repository.ChatRoomRepository;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        public List<ChatRoom> findAll() {
            return chatRoomRepository.findAll();
        }

        //  특정 채팅방 조회
        public ChatRoom findChatRoomByRoomId(String roomId){
            return chatRoomRepository.findChatRoomByRoomId(roomId);
        }

        //  채팅방 생성(멤버 필터)
//        public ChatRoom createChatRoom(ChatRoomDTO chatRoomDTO){
//            ChatRoom chatRoom = ChatRoom.builder ()
//                    .roomId ( chatRoomDTO.getRoomId () )
//                    .chatRoomName ( chatRoomDTO.getChatRoomName () )
//                    .creatorId ( chatRoomDTO.getCreatorId () )
//                    .createdAt ( chatRoomDTO.getCreatedAt () )
//                    .memberCount ( chatRoomDTO.getMemberCount () )
//                    .build ();
//            return chatRoomRepository.save (chatRoom);
//        }

        //  특정 채팅방 입장
        public ChatRoom joinChatRoom (String roomId, String userId) {
            ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId ( roomId );

            if (chatRoom == null) {
                throw new IllegalArgumentException ("채팅방이 존재하지 않습니다.");
            }
            chatRoom.setMemberCount(chatRoom.getMemberCount () + 1);
            return chatRoomRepository.save ( chatRoom );
        }

        //  특정 채팅방 퇴장
        public ChatRoom leaveChatRoom(String roomId, Long userId) {
            ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId ( roomId );
            if (chatRoom == null) {
                throw new IllegalArgumentException ("채팅방이 존재하지 않습니다.");
            }
            chatRoom.setMemberCount ( chatRoom.getMemberCount () - 1 );
            return chatRoomRepository.save ( chatRoom );
        }

        //  채팅방 채팅 작성
        public ChatMessage createMessage(ChatMessageDTO chatMessageDTO) {
            ChatMessage chatMessage = ChatMessage.builder ()
                    .messageId ( chatMessageDTO.getMessageId () )
                    .message ( chatMessageDTO.getMessage () )
                    .roomId ( chatMessageDTO.getRoomId () )
                    .senderId ( chatMessageDTO.getSenderId () )
                    .sentTime ( chatMessageDTO.getSentTime () )
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

