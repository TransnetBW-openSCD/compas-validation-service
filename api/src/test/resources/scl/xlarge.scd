<?xml version="1.0" encoding="UTF-8"?>
<SCL xmlns="http://www.iec.ch/61850/2003/SCL" version="2007" revision="B">
  <Header id="XL_SCD" version="3" revision="0" toolID="XLTool"/>

  <Substation name="SS_XL">
    <VoltageLevel name="VL1" nomFreq="50">
      <Bay name="Bay1"/><Bay name="Bay2"/><Bay name="Bay3"/><Bay name="Bay4"/>
    </VoltageLevel>
    <VoltageLevel name="VL2" nomFreq="50">
      <Bay name="Bay5"/><Bay name="Bay6"/><Bay name="Bay7"/>
    </VoltageLevel>
    <VoltageLevel name="VL3" nomFreq="50">
      <Bay name="Bay8"/><Bay name="Bay9"/>
    </VoltageLevel>
  </Substation>

  <Communication>
    <SubNetwork name="StationBus1" type="8-MMS">
      <ConnectedAP iedName="IED1" apName="AP1"><Address><P type="IP">192.168.1.11</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED2" apName="AP1"><Address><P type="IP">192.168.1.12</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED3" apName="AP1"><Address><P type="IP">192.168.1.13</P></Address></ConnectedAP>
    </SubNetwork>
    <SubNetwork name="StationBus2" type="8-MMS">
      <ConnectedAP iedName="IED4" apName="AP1"><Address><P type="IP">192.168.2.11</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED5" apName="AP1"><Address><P type="IP">192.168.2.12</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED6" apName="AP1"><Address><P type="IP">192.168.2.13</P></Address></ConnectedAP>
    </SubNetwork>
    <SubNetwork name="StationBus3" type="8-MMS">
      <ConnectedAP iedName="IED7" apName="AP1"><Address><P type="IP">192.168.3.11</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED8" apName="AP1"><Address><P type="IP">192.168.3.12</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED9" apName="AP1"><Address><P type="IP">192.168.3.13</P></Address></ConnectedAP>
      <ConnectedAP iedName="IED10" apName="AP1"><Address><P type="IP">192.168.3.14</P></Address></ConnectedAP>
    </SubNetwork>
  </Communication>

  <!-- IEDs 1–10 -->
  <IED name="IED1" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT1">
        <LN0 lnClass="LLN0">
          <DataSet name="dsEvents1"><FCDA ldInst="LD_PROT1" lnClass="PTOC" fc="ST"/></DataSet>
          <ReportControl name="rcEvents1" datSet="dsEvents1" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="PTOC" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED2" manufacturer="ACME" type="Breaker">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT2">
        <LN0 lnClass="LLN0">
          <DataSet name="dsAlarms2"><FCDA ldInst="LD_PROT2" lnClass="XCBR" fc="ST"/></DataSet>
          <ReportControl name="rcAlarms2" datSet="dsAlarms2" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="XCBR" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED3" manufacturer="ACME" type="Measurement">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_MEAS3">
        <LN0 lnClass="LLN0">
          <DataSet name="dsMeas3"><FCDA ldInst="LD_MEAS3" lnClass="MMXU" fc="MX"/></DataSet>
          <ReportControl name="rcMeas3" datSet="dsMeas3" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="MMXU" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED4" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT4">
        <LN0 lnClass="LLN0">
          <DataSet name="dsEvents4"><FCDA ldInst="LD_PROT4" lnClass="PTOC" fc="ST"/></DataSet>
          <ReportControl name="rcEvents4" datSet="dsEvents4" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="PTOC" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED5" manufacturer="ACME" type="Breaker">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT5">
        <LN0 lnClass="LLN0">
          <DataSet name="dsAlarms5"><FCDA ldInst="LD_PROT5" lnClass="XCBR" fc="ST"/></DataSet>
          <ReportControl name="rcAlarms5" datSet="dsAlarms5" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="XCBR" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED6" manufacturer="ACME" type="Measurement">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_MEAS6">
        <LN0 lnClass="LLN0">
          <DataSet name="dsMeas6"><FCDA ldInst="LD_MEAS6" lnClass="MMXU" fc="MX"/></DataSet>
          <ReportControl name="rcMeas6" datSet="dsMeas6" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="MMXU" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED7" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT7">
        <LN0 lnClass="LLN0">
          <DataSet name="dsEvents7"><FCDA ldInst="LD_PROT7" lnClass="PTOC" fc="ST"/></DataSet>
          <ReportControl name="rcEvents7" datSet="dsEvents7" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="PTOC" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED8" manufacturer="ACME" type="Breaker">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT8">
        <LN0 lnClass="LLN0">
          <DataSet name="dsAlarms8"><FCDA ldInst="LD_PROT8" lnClass="XCBR" fc="ST"/></DataSet>
          <ReportControl name="rcAlarms8" datSet="dsAlarms8" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="XCBR" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED9" manufacturer="ACME" type="Measurement">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_MEAS9">
        <LN0 lnClass="LLN0">
          <DataSet name="dsMeas9"><FCDA ldInst="LD_MEAS9" lnClass="MMXU" fc="MX"/></DataSet>
          <ReportControl name="rcMeas9" datSet="dsMeas9" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="MMXU" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

  <IED name="IED10" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1"><Server>
      <LDevice inst="LD_PROT10">
        <LN0 lnClass="LLN0">
          <DataSet name="dsEvents10"><FCDA ldInst="LD_PROT10" lnClass="PTOC" fc="ST"/></DataSet>
          <ReportControl name="rcEvents10" datSet="dsEvents10" confRev="1" buffered="true"/>
        </LN0>
        <LN lnClass="PTOC" inst="1"/>
      </LDevice>
    </Server></AccessPoint>
  </IED>

</SCL>
