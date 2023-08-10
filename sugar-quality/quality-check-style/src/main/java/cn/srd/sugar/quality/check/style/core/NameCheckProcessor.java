// package cn.srd.itcp.sugar.component.check.style.core;
//
// import javax.annotation.processing.AbstractProcessor;
// import javax.annotation.processing.ProcessingEnvironment;
// import javax.annotation.processing.RoundEnvironment;
// import javax.annotation.processing.SupportedAnnotationTypes;
// import javax.lang.model.element.Element;
// import javax.lang.model.element.TypeElement;
// import java.util.Set;
//
// // 可以用"*"表示支持所有Annotations
// @SupportedAnnotationTypes("*")
// public class NameCheckProcessor extends AbstractProcessor {
//
//     private NameChecker nameChecker;
//
//     /**
//      * 初始化名称检查插件
//      */
//     @Override
//     public void init(ProcessingEnvironment processingEnv) {
//         super.init(processingEnv);
//         nameChecker = new NameChecker(processingEnv);
//     }
//
//     /**
//      * 对输入的语法树的各个节点进行进行名称检查
//      */
//     @Override
//     public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//         if (!roundEnv.processingOver()) {
//             for (Element element : roundEnv.getRootElements())
//                 nameChecker.checkNames(element);
//         }
//         return false;
//     }
// }