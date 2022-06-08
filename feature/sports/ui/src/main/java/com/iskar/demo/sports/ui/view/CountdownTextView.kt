package com.iskar.demo.sports.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import com.iskar.demo.core.ui.ext.getString
import com.iskar.demo.core.ui.ext.launchInViewScope
import com.iskar.demo.core.utils.tickerFlow
import com.iskar.demo.feature.sports.ui.R
import kotlinx.coroutines.Job
import kotlin.math.abs
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class CountdownTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    var job: Job? = null

    fun setTime(endTime: Long?) {
        endTime ?: return
        doOnAttach {
            job?.cancel()
            job = launchInViewScope {
                tickerFlow().collect {
                    val duration = endTime - System.currentTimeMillis() / 1000
                    val timeLeft: Duration = abs(duration).toDuration(DurationUnit.SECONDS)
                    timeLeft.toComponents { hours, minutes, seconds, _ ->
                        val pattern = if (duration > 0) {
                            getString(R.string.sports_time_left)
                        } else {
                            getString(R.string.sports_negative_time_left)
                        }
                        text = String.format(pattern, hours, minutes, seconds)
                    }
                }
            }
        }
        doOnDetach {
            job?.cancel()
        }
    }

}