package com.uravatech.ast.addition.view.bean;

//import com.uravatech.commonui.bean.UravaMasterPageBean;
//import com.uravatech.commonui.utils.ADFUtils;
//import com.uravatech.commonui.utils.JSFUtils;

//import com.uravatech.commonui.utils.UravaADFLogger;

//import java.util.ResourceBundle;

//import oracle.adf.model.binding.DCIteratorBinding;

//import oracle.jbo.Row;


import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;
public class AstTAdditionBean extends UravaMasterPageBean {
    private static UravaADFLogger _logger = UravaADFLogger.createUravaAdfLogger(AstTAdditionBean.class);
   private boolean quickQueryVisible = true;
    final ResourceBundle errorMessagesBundle =                                                      
                                    ResourceBundle.getBundle("com.uravatech.commonlabel.AstTAddition");
    public AstTAdditionBean() {
    }
    public void setQuickQueryVisible(boolean quickQueryVisible) {
        this.quickQueryVisible = quickQueryVisible;
    }

    public boolean isQuickQueryVisible() {
        return quickQueryVisible;
    }

    @Override
    public boolean beforeSave() {
        // TODO Implement this method
        DCIteratorBinding detailIter = ADFUtils.findIterator("AstMAsset1Iterator");
        Row headerRow = ADFUtils.findIterator("AstTTransactionHead1Iterator").getCurrentRow();
        if (detailIter.getEstimatedRowCount() <= 0) {
            JSFUtils.showWarningMessage(errorMessagesBundle.getString("DETAIL_BLANK"));
            return false;
        }
        if (headerRow.getAttribute("DocNum").equals("TEMP")) {
            ADFUtils.doOperation("docNumModuleWise");
        }
        _logger.info("DocInum==" + headerRow.getAttribute("DocInum"));
        _logger.info("Entering before save as");
        
     
        _logger.info("Exiting");
        return true;
    }
}
