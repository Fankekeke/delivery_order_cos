<template>
  <a-modal v-model="show" title="修改提现记录" @cancel="onClose" :width="700">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='提现记录名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入提现记录名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='原料' v-bind="formItemLayout">
            <a-input v-decorator="[
            'rawMaterial',
            { rules: [{ required: true, message: '请输入原料!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='份量' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'portion',
            { rules: [{ required: true, message: '请输入份量!' }] }
            ]" :min="1" :step="1"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='口味' v-bind="formItemLayout">
            <a-input v-decorator="[
            'taste',
            { rules: [{ required: true, message: '请输入口味!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='价格' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'unitPrice',
            { rules: [{ required: true, message: '请输入价格!' }] }
            ]" :min="0.1" :step="0.1"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='提现记录状态' v-bind="formItemLayout">
            <a-select v-decorator="[
              'status',
              { rules: [{ required: true, message: '请输入提现记录状态!' }] }
              ]">
              <a-select-option value="0">下架</a-select-option>
              <a-select-option value="1">上架</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='热量含量' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'heat',
            { rules: [{ required: true, message: '请输入热量含量!' }] }
            ]" :min="0.1" :step="0.1"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='蛋白质含量' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'protein',
            { rules: [{ required: true, message: '请输入蛋白质含量!' }] }
            ]" :min="0.1" :step="0.1"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='脂肪含量' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'fat',
            { rules: [{ required: true, message: '请输入脂肪含量!' }] }
            ]" :min="0.1" :step="0.1"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='提现记录描述' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
            'content',
             { rules: [{ required: true, message: '请输入提现记录描述!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='图册' v-bind="formItemLayout">
            <a-upload
              name="avatar"
              action="http://127.0.0.1:9527/file/fileUpload/"
              list-type="picture-card"
              :file-list="fileList"
              @preview="handlePreview"
              @change="picHandleChange"
            >
              <div v-if="fileList.length < 8">
                <a-icon type="plus" />
                <div class="ant-upload-text">
                  Upload
                </div>
              </div>
            </a-upload>
            <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
              <img alt="example" style="width: 100%" :src="previewImage" />
            </a-modal>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'withdrawEdit',
  props: {
    withdrawEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.withdrawEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  methods: {
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    setFormValues ({...withdraw}) {
      this.rowId = withdraw.id
      let fields = ['name', 'content', 'rawMaterial', 'portion', 'taste', 'unitPrice', 'status', 'heat', 'protein', 'fat']
      let obj = {}
      Object.keys(withdraw).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(withdraw['images'])
        }
        if (key === 'status') {
          withdraw[key] = withdraw[key].toString()
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = withdraw[key]
        }
      })
      this.form.setFieldsValue(obj)
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        if (image.response !== undefined) {
          images.push(image.response)
        } else {
          images.push(image.name)
        }
      })
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          this.loading = true
          this.$put('/cos/withdraw-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
