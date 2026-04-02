<?xml version="1.0" encoding="UTF-8"?>
<SCL xmlns="http://www.iec.ch/61850/2003/SCL" version="2007" revision="B">
  <Header id="MEDIUM_SCD" version="1" revision="0" toolID="MediumTool"/>

  <Substation name="SS_MED">
    <VoltageLevel name="VL1" nomFreq="50">
      <Bay name="Bay1"/>
      <Bay name="Bay2"/>
    </VoltageLevel>
    <VoltageLevel name="VL2" nomFreq="50">
      <Bay name="Bay3"/>
      <Bay name="Bay4"/>
    </VoltageLevel>
  </Substation>

  <Communication>
    <SubNetwork name="StationBus" type="8-MMS">
      <ConnectedAP iedName="IED1" apName="AP1">
        <Address>
          <P type="IP">192.168.1.11</P>
        </Address>
      </ConnectedAP>
      <ConnectedAP iedName="IED2" apName="AP1">
        <Address>
          <P type="IP">192.168.1.12</P>
        </Address>
      </ConnectedAP>
    </SubNetwork>
  </Communication>

  <IED name="IED1" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1">
      <Server>
        <LDevice inst="LD_PROT1">
          <LN0 lnClass="LLN0">
            <DataSet name="dsEvents">
              <FCDA ldInst="LD_PROT1" lnClass="PTOC" fc="ST"/>
            </DataSet>
            <ReportControl name="rcEvents" datSet="dsEvents" confRev="1" buffered="true" rptID="IED1/LLN0$Events"/>
          </LN0>
          <LN lnClass="PTOC" inst="1"/>
        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

  <IED name="IED2" manufacturer="ACME" type="Breaker">
    <AccessPoint name="AP1">
      <Server>
        <LDevice inst="LD_PROT2">
          <LN0 lnClass="LLN0">
            <DataSet name="dsAlarms">
              <FCDA ldInst="LD_PROT2" lnClass="XCBR" fc="ST"/>
            </DataSet>
            <ReportControl name="rcAlarms" datSet="dsAlarms" confRev="1" buffered="true" rptID="IED2/LLN0$Alarms"/>
          </LN0>
          <LN lnClass="XCBR" inst="1"/>
        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

</SCL>
