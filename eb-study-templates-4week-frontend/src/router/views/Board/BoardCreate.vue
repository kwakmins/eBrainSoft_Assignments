<!--게시판 작성 화면-->

<script setup>
import {onMounted, ref} from 'vue'
import axios from "axios";

const passwordRegexp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{4,16}$/

const rules = {
  required: value => !!value,
  password: v => passwordRegexp.test(String(v)),
  userName: v => v.length >= 3 && v.length < 5,
  title: v => v.length >= 4 && v.length < 100,
  content: v => v.length >= 4 && v.length < 2000
}

const show1 = ref(false)
const show2 = ref(true)
const password = ref('Password')

const items = ref(new Map())


/**
 * 카테고리 불러오기.
 */
axios.get("http://localhost:8080/api/v1/board/write")
    .then((res) => {
      res.data.categories.forEach(function (category) {
            items.value.set(category.id, category.name)
          }
      )
    })
    .catch((res) => {
      console.error(res)
    })

/**
 * 게시판 작성
 */
function createBoard() {
  axios.post("http://localhost:8080/api/v1/board/write", "request : as," +
      "uploadFiles : asdf", {
    headers: {"Content-Type": "multipart/form-data"}
  })
      .then((res) => {
            console.log(res)
          }
      )
      .catch((res) => {
        console.log(res)
      })
}
</script>

<template>
  <v-container fluid>

    <v-row>
      <v-col cols="4">
        <v-list-subheader>카테고리</v-list-subheader>
      </v-col>

      <v-col cols="3">
        <v-combobox
            label="카테고리 선택"
            :rules="[rules.require]"
            :items="items.values()"
        ></v-combobox>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4">
        <v-list-subheader>작성자</v-list-subheader>
      </v-col>

      <v-col cols="3">
        <v-text-field
            :rules="[rules.required,rules.userName]"
            hint="3글자 이상 5글자 미만"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4">
        <v-list-subheader>비밀번호</v-list-subheader>
      </v-col>

      <v-col cols="3">
        <v-text-field
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.password]"
            :type="show1 ? 'text' : 'password'"
            name="input-10-1"
            label="비밀번호"
            hint="4~16글자,영문/숫자/특수문자 포함"
            counter
            @click:append="show1 = !show1"
        ></v-text-field>
      </v-col>

      <v-col cols="3">
        <v-text-field
            :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.password]"
            :type="show2 ? 'text' : 'password'"
            name="input-10-2"
            label="비밀번호 확인"
            class="input-group--focused"
            @click:append="show2 = !show2"
        ></v-text-field>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4">
        <v-list-subheader>제목</v-list-subheader>
      </v-col>

      <v-col cols="8">
        <v-text-field
            :rules="[rules.required,rules.title]"
            hint="4~100 글자"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4">
        <v-list-subheader>내용</v-list-subheader>
      </v-col>

      <v-col cols="8">
        <v-textarea
            :rules="[rules.required,rules.content]"
            hint="4~2000 글자"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4">
        <v-list-subheader>파일 첨부</v-list-subheader>
      </v-col>

      <v-col cols="6">
        <div>
          <v-file-input/>
          <v-file-input/>
          <v-file-input/>
        </div>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="2">
        <v-btn color="red"> 취소</v-btn>
      </v-col>

      <v-col cols="8"/>

      <v-col cols="2">
        <v-btn color="blue" @click="createBoard()"> 저장</v-btn>
      </v-col>

    </v-row>


  </v-container>
</template>


<style scoped>

</style>