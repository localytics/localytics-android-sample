package com.localytics.demo.ui.screens.features.inbox

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.localytics.androidx.InboxListAdapter
import com.localytics.androidx.Localytics
import com.localytics.demo.R


class InboxActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inbox)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listView = findViewById(R.id.listView)
        listView.onItemClickListener = this
        val inboxListAdapter = InboxListAdapter(this,listView)
        listView.adapter = inboxListAdapter
        inboxListAdapter.getData(null)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val inboxListAdapter = parent!!.adapter as InboxListAdapter
        val campaign = inboxListAdapter.getItem(position)
        campaign!!.isRead = true

        inboxListAdapter.notifyDataSetChanged()

        if (campaign.hasCreative()) {
            val intent = Intent(
                this,
                InboxCampaignActivity::class.java
            )
            intent.putExtra("campaign", campaign)
            startActivity(intent)
        } else {
            Localytics.inboxListItemTapped(campaign)
        }
    }
}