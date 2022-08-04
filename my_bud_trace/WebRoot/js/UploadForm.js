var vueform = {
    data() {
        return {
            formInline: {
                thename: '',
                thedesc: '',
                thetype: '',
            },
            rules: {
                thename: [
                    {
                        required: true,
                        message: '名称待完善'
                    }
                ],
                thedesc: [
                    {
                        required: true,
                        message: '描述待完善'
                    }
                ],
                thetype: [
                    {
                        required: true,
                        message: '需要选择类别'
                    }
                ],
            }
        }
    },
    methods: {
        subMit() {
            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    if (nowtype != null){
                    show(this.formInline.thename,this.formInline.thedesc,this.formInline.thetype,nowtype)
                    //清空表单
                    this.formInline = this.$options.data().formInline
                }else{
                    danger()
                    pointime._data.result = "尚未定义新增围栏";
                }
                } else {
                    danger()
                    pointime._data.result = "请完善围栏信息！";
                }
            });
            
        },
    }
}
var Ctor = Vue.extend(vueform)
new Ctor().$mount('#app1')
var clicktag = false
var showform =function(){
    if (clicktag){
        clicktag = false
        $('#app1').css({'display': 'none'})
        $('#upinner').text('电子围栏上传')
    }else{
        clicktag = true
        $('#app1').css({'display': 'block'})
        $('#upinner').text('隐藏围栏表单')
    }
}