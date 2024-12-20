package com.localytics.demo.ui.screens.features.inbox

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.localytics.androidx.InboxCampaign
import com.localytics.androidx.InboxDetailSupportFragment
import com.localytics.demo.R

class InboxCampaignActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox_campaign)

        if (savedInstanceState == null) {
            val campaign = intent.getParcelableExtra<InboxCampaign>("campaign")
            val fragment = InboxDetailSupportFragment.newInstance(campaign!!)
            supportFragmentManager.beginTransaction()
                .add(R.id.campaign_container, fragment)
                .commit()
        }
    }
}