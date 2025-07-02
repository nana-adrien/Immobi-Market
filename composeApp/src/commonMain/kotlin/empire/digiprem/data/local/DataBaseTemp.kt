package empire.digiprem.data.local

import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.enums.chat.ConversationTypeEnum
import empire.digiprem.enums.chat.MessageStatusEnum
import empire.digiprem.enums.chat.MessageTypeEnum
import empire.digiprem.model.chat.Conversation
import empire.digiprem.model.chat.Message
import empire.digiprem.presentation.views.NotificationItem

object DataBaseTemp {
    var tokenTable: Map<String, String> = emptyMap()

    val testConversations = mutableListOf(
        Conversation(
            id = 1,
            selected = false,
            online = true,
            title = "Sarah",
            sender = "Sarah",
            lastSeen = "2025-06-14T08:20:00",
            unreadCount = 2,
            typing = true,
            pinned = false,
            muted = false,
            archived = false,
            blocked = false,
            favorite = true,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.RECEIVED,
            timeIndicator = "08:19",
            messages = mutableListOf(
                Message(
                    id = "m1",
                    userName = "Sarah",
                    content = "Regarde ce paysage !",
                    imageUrl = "https://FILEs.unsplash.com/photo-1506744038136-46273834b3fb",
                    isSender = false,
                    timestamp = "2025-06-14T08:19:00",
                    messageType = MessageTypeEnum.FILE
                )
            )
        ),
        Conversation(
            id = 2,
            selected = false,
            online = false,
            title = "Alex",
            sender = "Alex",
            lastSeen = "2025-06-13T23:40:00",
            unreadCount = 0,
            typing = false,
            pinned = true,
            muted = false,
            archived = false,
            blocked = false,
            favorite = false,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.SEND,
            timeIndicator = "Hier",
            messages = mutableListOf(
                Message(
                    id = "m2",
                    userName = "Moi",
                    content = "Tu confirmes pour demain ?",
                    isSender = true,
                    timestamp = "2025-06-13T22:40:00",
                    messageType = MessageTypeEnum.TEXT
                )
            )
        ),
        Conversation(
            id = 3,
            selected = false,
            online = true,
            title = "Projet Mobile",
            sender = "Jean",
            lastSeen = "2025-06-14T07:00:00",
            unreadCount = 5,
            typing = false,
            pinned = false,
            muted = true,
            archived = false,
            blocked = false,
            favorite = true,
            conversationType = ConversationTypeEnum.GROUP,
            status = MessageStatusEnum.SEND,
            timeIndicator = "07:30",
            messages = mutableListOf(
                Message(
                    id = "m3",
                    userName = "Jean",
                    content = "Voici la nouvelle maquette",
                    imageUrl = "https://FILEs.pexels.com/photos/3184287/pexels-photo-3184287.jpeg",
                    isSender = false,
                    timestamp = "2025-06-14T07:20:00",
                    messageType = MessageTypeEnum.FILE
                )
            )
        ),
        Conversation(
            id = 4,
            selected = false,
            online = false,
            title = "Maman",
            sender = "Maman",
            lastSeen = "2025-06-12T19:15:00",
            unreadCount = 0,
            typing = false,
            pinned = false,
            muted = false,
            archived = false,
            blocked = false,
            favorite = true,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.SEND,
            timeIndicator = "12 juin",
            messages = mutableListOf(
                Message(
                    id = "m4",
                    userName = "Maman",
                    content = "N'oublie pas de passer demain ❤️",
                    isSender = false,
                    timestamp = "2025-06-12T19:10:00",
                    messageType = MessageTypeEnum.TEXT
                )
            )
        ),
        Conversation(
            id = 5,
            selected = false,
            online = true,
            title = "Thomas",
            sender = "Thomas",
            lastSeen = "2025-06-14T09:10:00",
            unreadCount = 1,
            typing = true,
            pinned = false,
            muted = false,
            archived = false,
            blocked = false,
            favorite = false,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.RECEIVED,
            timeIndicator = "09:08",
            messages = mutableListOf(
                Message(
                    id = "m5",
                    userName = "Thomas",
                    content = "Je suis en chemin.",
                    isSender = false,
                    timestamp = "2025-06-14T09:08:00",
                    messageType = MessageTypeEnum.TEXT
                )
            )
        ),
        Conversation(
            id = 6,
            selected = false,
            online = false,
            title = "Lisa",
            sender = "Lisa",
            lastSeen = "2025-06-13T18:00:00",
            unreadCount = 0,
            typing = false,
            pinned = false,
            muted = true,
            archived = false,
            blocked = false,
            favorite = false,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.SEND,
            timeIndicator = "Hier",
            messages = mutableListOf(
                Message(
                    id = "m6",
                    userName = "Lisa",
                    content = "Bonne soirée !",
                    isSender = false,
                    timestamp = "2025-06-13T18:00:00",
                    messageType = MessageTypeEnum.TEXT
                )
            )
        ),
        Conversation(
            id = 7,
            selected = false,
            online = true,
            title = "Groupe Famille",
            sender = "Papa",
            lastSeen = "2025-06-14T06:10:00",
            unreadCount = 3,
            typing = false,
            pinned = true,
            muted = false,
            archived = false,
            blocked = false,
            favorite = false,
            conversationType = ConversationTypeEnum.GROUP,
            status = MessageStatusEnum.RECEIVED,
            timeIndicator = "06:10",
            messages = mutableListOf(
                Message(
                    id = "m7",
                    userName = "Papa",
                    content = "Regardez cette photo de nous 🥰",
                    imageUrl = "https://FILEs.pexels.com/photos/52570/pexels-photo-52570.jpeg",
                    isSender = false,
                    timestamp = "2025-06-14T06:10:00",
                    messageType = MessageTypeEnum.FILE
                )
            )
        ),
        Conversation(
            id = 8,
            selected = false,
            online = false,
            title = "Équipe Dev",
            sender = "Elodie",
            lastSeen = "2025-06-13T11:00:00",
            unreadCount = 0,
            typing = false,
            pinned = false,
            muted = false,
            archived = true,
            blocked = false,
            favorite = false,
            conversationType = ConversationTypeEnum.GROUP,
            status = MessageStatusEnum.SEND,
            timeIndicator = "Hier",
            messages = mutableListOf(
                Message(
                    id = "m8",
                    userName = "Elodie",
                    content = "Le bug est corrigé.",
                    isSender = false,
                    timestamp = "2025-06-13T11:00:00",
                    messageType = MessageTypeEnum.TEXT
                )
            )
        ),
        Conversation(
            id = 9,
            selected = false,
            online = false,
            title = "Samuel",
            sender = "Samuel",
            lastSeen = "2025-06-10T20:00:00",
            unreadCount = 0,
            typing = false,
            pinned = false,
            muted = false,
            archived = true,
            blocked = false,
            favorite = false,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.SEND,
            timeIndicator = "10 juin",
            messages = mutableListOf(
                Message(
                    id = "m9",
                    userName = "Samuel",
                    content = "On se voit ce week-end ?",
                    isSender = false,
                    timestamp = "2025-06-10T20:00:00",
                    messageType = MessageTypeEnum.TEXT
                )
            )
        ),
        Conversation(
            id = 10,
            selected = false,
            online = true,
            title = "Claire",
            sender = "Claire",
            lastSeen = "2025-06-14T08:45:00",
            unreadCount = 1,
            typing = false,
            pinned = false,
            muted = false,
            archived = false,
            blocked = false,
            favorite = true,
            conversationType = ConversationTypeEnum.PRIVATE,
            status = MessageStatusEnum.RECEIVED,
            timeIndicator = "08:44",
            messages = mutableListOf(
                Message(
                    id = "m10",
                    userName = "Claire",
                    content = "Voici l'affiche du projet 👇",
                    imageUrl = "https://FILEs.unsplash.com/photo-1529333166437-7750a6dd5a70",
                    isSender = false,
                    timestamp = "2025-06-14T08:44:00",
                    messageType = MessageTypeEnum.FILE
                )
            )
        )
    )
    var isConnected = false
    var token=""
    var userProfile: GetProfileResponse?=null
    val notifications = listOf(
        NotificationItem(
            id = 1,
            title = "Bienvenue sur DigiPrem !",
            body = "Votre inscription a été complétée avec succès.",
            time = "2025-06-29 08:15",
            isRead = false
        ),
        NotificationItem(
            id = 2,
            title = "Nouvelle offre disponible",
            body = "Une nouvelle offre de studio est disponible à Bonamoussadi.",
            time = "2025-06-29 10:42",
            isRead = false
        ),
        NotificationItem(
            id = 3,
            title = "Mot de passe changé",
            body = "Votre mot de passe a été modifié avec succès.",
            time = "2025-06-28 17:00",
            isRead = true
        ),
        NotificationItem(
            id = 4,
            title = "Paiement reçu",
            body = "Votre paiement pour l'abonnement premium a été reçu.",
            time = "2025-06-27 14:23",
            isRead = true
        ),
        NotificationItem(
            id = 5,
            title = "Message de l'administrateur",
            body = "Merci d’utiliser nos services. Nous restons à votre disposition.",
            time = "2025-06-26 09:00",
            isRead = false
        )
    )

}