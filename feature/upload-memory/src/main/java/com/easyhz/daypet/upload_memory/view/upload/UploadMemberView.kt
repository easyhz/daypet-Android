package com.easyhz.daypet.upload_memory.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.image.MemberImage
import com.easyhz.daypet.design_system.component.image.MemberSelectType
import com.easyhz.daypet.design_system.component.main.DayPetRow
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.domain.model.member.GroupUser
import com.easyhz.daypet.domain.model.member.Pet
import com.easyhz.daypet.domain.model.upload.Member

@Composable
fun UploadMemberView(
    modifier: Modifier = Modifier,
    members: List<Member>,
    onAddClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(id = R.string.upload_member),
            style = SubBody2,
            color = Primary
        )
        DayPetRow {
            items(members) {
                MemberImage(
                    selectType = MemberSelectType.DELETE,
                    imageUrl = it.thumbnail,
                    name = it.name,
                    isChecked = true
                ) {
                    println("it > $it")
                }
            }
            item {
                MemberImage(
                    selectType = MemberSelectType.ADD,
                    imageUrl = "",
                    name = "",
                    onClick = onAddClick
                )
            }
        }
    }
}

@Composable
internal fun MemberSelectView(
    modifier: Modifier = Modifier,
    pets: List<Pet>,
    members: List<GroupUser>,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 4.dp),
            text = stringResource(id = R.string.upload_member_select),
            style = SubBody2,
            color = Primary
        )

        DayPetRow {
            items(pets) {
                MemberImage(
                    selectType = MemberSelectType.CHECK,
                    imageUrl = it.thumbnailUrl,
                    name = it.name,
                    isChecked = false
                ) {
                    println("it > $it")
                }
            }
        }
        DayPetRow {
            items(members) {
                MemberImage(
                    selectType = MemberSelectType.CHECK,
                    imageUrl = it.thumbnailUrl,
                    name = it.name,
                    isChecked = false
                ) {
                    println("it > $it")
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        MainButton(
            text = stringResource(id = R.string.button_select_success),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

        }
    }
}