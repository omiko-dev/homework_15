package com.example.shemajamebeli__5.contacts.recycler


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shemajamebeli__5.contacts.model.ContactItem
import com.example.shemajamebeli__5.contacts.model.MessageType
import com.example.shemajamebeli__5.databinding.MessagesBoxFileBinding
import com.example.shemajamebeli__5.databinding.MessagesBoxTextBinding
import com.example.shemajamebeli__5.databinding.MessagesBoxVoiceBinding

class MessageBoxRecyclerAdapter :
    ListAdapter<ContactItem, RecyclerView.ViewHolder>(ItemDiffUtil()) {

    class ItemDiffUtil : DiffUtil.ItemCallback<ContactItem>() {
        override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class MessageBoxTextViewHolder(private val binding: MessagesBoxTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            bindContactItem(item)
            checkUnreadMessage(item)
            checkTyping(item)
        }

        private fun bindContactItem(item: ContactItem) {
            with(binding) {
                tvUserName.text = item.owner
                tvTime.text = item.last_active
                tvMessage.text = item.last_message
                Glide.with(itemView)
                    .load(item.image)
                    .into(ivImage)
            }
        }

        private fun checkUnreadMessage(item: ContactItem) {
            with(binding) {
                if (item.unread_messages > 0) {
                    tvUserName.setTextColor(Color.WHITE)
                    tvTime.setTextColor(Color.WHITE)
                    tvMessage.setTextColor(Color.WHITE)
                }

                if (item.unread_messages > 1) {
                    tvTyping.visibility = View.GONE
                    tvMissMessage.visibility = View.VISIBLE
                    tvMissMessage.text = item.unread_messages.toString()
                }
            }
        }

        private fun checkTyping(item: ContactItem) {
            with(binding) {
                if (item.is_typing) {
                    tvTyping.visibility = View.VISIBLE
                    tvMissMessage.visibility = View.GONE
                } else {
                    tvTyping.visibility = View.GONE
                }
            }
        }
    }

    inner class MessageBoxFileViewHolder(private val binding: MessagesBoxFileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            bindContactItem(item)
            checkUnreadMessage(item)
            checkTyping(item)
        }

        private fun bindContactItem(item: ContactItem) {
            with(binding) {
                tvUserName.text = item.owner
                tvTime.text = item.last_active
                Glide.with(itemView)
                    .load(item.image)
                    .into(ivImage)
            }
        }

        private fun checkUnreadMessage(item: ContactItem) {
            with(binding) {
                if (item.unread_messages > 0) {
                    tvUserName.setTextColor(Color.WHITE)
                    tvTime.setTextColor(Color.WHITE)
                    tvMessage.setTextColor(Color.WHITE)
                }

                if (item.unread_messages > 1) {
                    tvTyping.visibility = View.GONE
                    tvMissMessage.visibility = View.VISIBLE
                    tvMissMessage.text = item.unread_messages.toString()
                }
            }
        }

        private fun checkTyping(item: ContactItem) {
            with(binding) {
                if (item.is_typing) {
                    tvTyping.visibility = View.VISIBLE
                    tvMissMessage.visibility = View.GONE
                } else {
                    tvTyping.visibility = View.GONE
                }
            }
        }
    }

    inner class MessageBoxVoiceViewHolder(private val binding: MessagesBoxVoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            bindContactItem(item)
            checkUnreadMessage(item)
            checkTyping(item)
        }

        private fun bindContactItem(item: ContactItem){
            with(binding) {
                tvUserName.text = item.owner
                tvTime.text = item.last_active
                Glide.with(itemView)
                    .load(item.image)
                    .into(ivImage)
            }
        }

        private fun checkUnreadMessage(item: ContactItem){
            with(binding) {
                if (item.unread_messages > 0) {
                    tvUserName.setTextColor(Color.WHITE)
                    tvTime.setTextColor(Color.WHITE)
                    tvMessage.setTextColor(Color.WHITE)
                }

                if (item.unread_messages > 1) {
                    tvTyping.visibility = View.GONE
                    tvMissMessage.visibility = View.VISIBLE
                    tvMissMessage.text = item.unread_messages.toString()
                }
            }
        }

        private fun checkTyping(item: ContactItem){
            with(binding){
                if (item.is_typing) {
                    tvTyping.visibility = View.VISIBLE
                    tvMissMessage.visibility = View.GONE
                } else {
                    tvTyping.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].laste_message_type) {
            MessageType.TEXT.type -> 1
            MessageType.FILE.type -> 2
            MessageType.VOICE.type -> 3
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                return MessageBoxTextViewHolder(
                    MessagesBoxTextBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            2 -> {
                return MessageBoxFileViewHolder(
                    MessagesBoxFileBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                return MessageBoxVoiceViewHolder(
                    MessagesBoxVoiceBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MessageBoxTextViewHolder) {
            holder.bind()
        }
        if (holder is MessageBoxFileViewHolder) {
            holder.bind()
        }
        if (holder is MessageBoxVoiceViewHolder) {
            holder.bind()
        }
    }

}