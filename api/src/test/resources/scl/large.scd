<?xml version="1.0" encoding="UTF-8"?>
<SCL xmlns="http://www.iec.ch/61850/2003/SCL" version="2007" revision="B">
  <Header id="LARGE_SCD" version="2" revision="0" toolID="LargeTool"/>

  <Substation name="SS_LARGE">
    <VoltageLevel name="VL1" nomFreq="50">
      <Bay name="Bay1"/>
      <Bay name="Bay2"/>
      <Bay name="Bay3"/>
    </VoltageLevel>
    <VoltageLevel name="VL2" nomFreq="50">
      <Bay name="Bay4"/>
      <Bay name="Bay5"/>
    </VoltageLevel>
  </Substation>

  <Communication>
    <SubNetwork name="StationBus1" type="8-MMS">
      <ConnectedAP iedName="IED1" apName="AP1">
        <Address><P type="IP">192.168.1.11</P></Address>
      </ConnectedAP>
      <ConnectedAP iedName="IED2" apName="AP1">
        <Address><P type="IP">192.168.1.12</P></Address>
      </ConnectedAP>
      <ConnectedAP iedName="IED3" apName="AP1">
        <Address><P type="IP">192.168.1.13</P></Address>
      </ConnectedAP>
    </SubNetwork>
    <SubNetwork name="StationBus2" type="8-MMS">
      <ConnectedAP iedName="IED4" apName="AP1">
        <Address><P type="IP">192.168.2.11</P></Address>
      </ConnectedAP>
      <ConnectedAP iedName="IED5" apName="AP1">
        <Address><P type="IP">192.168.2.12</P></Address>
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
            <ReportControl name="rcEvents" datSet="dsEvents" confRev="1" buffered="true"/>
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
            <ReportControl name="rcAlarms" datSet="dsAlarms" confRev="1" buffered="true"/>
          </LN0>
          <LN lnClass="XCBR" inst="1"/>
        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

  <IED name="IED3" manufacturer="ACME" type="Measurement">
    <AccessPoint name="AP1">
      <Server>
        <LDevice inst="LD_MEAS">
          <LN0 lnClass="LLN0">
            <DataSet name="dsMeas">
              <FCDA ldInst="LD_MEAS" lnClass="MMXU" fc="MX"/>
            </DataSet>
            <ReportControl name="rcMeas" datSet="dsMeas" confRev="1" buffered="true"/>
          </LN0>
          <LN lnClass="MMXU" inst="1"/>
        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

  <IED name="IED4" manufacturer="ACME" type="ProtectionRelay">
    <AccessPoint name="AP1">
      <Server>
        <LDevice inst="LD_PROT4">
          <LN0 lnClass="LLN0">
            <DataSet name="dsEvents4">
              <FCDA ldInst="LD_PROT4" lnClass="PTOC" fc="ST"/>
            </DataSet>
            <ReportControl name="rcEvents4" datSet="dsEvents4" confRev="1" buffered="true"/>
          </LN0>
          <LN lnClass="PTOC" inst="1"/>
        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

  <IED name="IED5" manufacturer="ACME" type="Breaker">
    <AccessPoint name="AP1">
      <Server>
        <LDevice inst="LD_PROT5">
          <LN0 lnClass="LLN0">
            <DataSet name="dsAlarms5">
              <FCDA ldInst="LD_PROT5" lnClass="XCBR" fc="ST"/>
            </DataSet>
            <ReportControl name="rcAlarms5" datSet="dsAlarms5" confRev="1" buffered="true"/>
          </LN0>
          <LN lnClass="XCBR" inst="1"/>
        </LDevice>
      </Server>
    </AccessPoint>
  </IED>

</SCL>
